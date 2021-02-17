package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader implements Readable {

	private String path = "";
	private BufferedReader reader = null;

	public FileReader(String path) {
		this.path = path;
		createNewFile(path);
		initializeFileReader();
	}

	private void createNewFile(String path) {
		try {
			File myObj = new File(path);
			myObj.createNewFile();
		} catch (IOException e) {
			System.err.println("An error occurred while creating file " + path + ".");
			e.printStackTrace();
		}
	}

	private void initializeFileReader() {
		try {
			reader = Files.newBufferedReader(Paths.get(path));
		} catch (InvalidPathException | IOException e) {
			System.err.println("An error occurred while opening file " + path + " for reading.");
			e.printStackTrace();
		}
	}

	@Override
	public String getType() {
		return "file";
	}

	@Override
	public String readLine() {
		String line = null;

		try {
			line = reader.readLine();

			// Test if end of file.
			if (line == null) {
				close();
			}
		} catch (IOException e) {
			System.err.println("An error occurred while reading from file " + path + ".");
			e.printStackTrace();
		}

		return line;
	}

	@Override
	public List<String> readAllLines() {
		List<String> allLines = new ArrayList<String>();
		String line = readLine();

		while (line != null) {
			allLines.add(line);
			line = readLine();
		}

		return allLines;
	}

	private void close() {
		try {
			// Close file.
			reader.close();
		} catch (IOException e) {
			System.err.println("An error occurred while closing file " + path + ".");
			e.printStackTrace();
		}
	}

}
