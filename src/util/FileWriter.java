package util;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileWriter implements Writable {

	private static final String LINE_SEPARATOR = System.lineSeparator();

	private String path = "";
	private java.io.FileWriter writer = null;

	public FileWriter(String path) {
		this.path = path;
		createNewFile(path);
		initializeFileWriter(true);
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

	private void initializeFileWriter(boolean append) {
		try {
			writer = new java.io.FileWriter(path, append);
		} catch (IOException e) {
			System.out.println("An error occurred while opening file " + path + " for writing.");
			e.printStackTrace();
		}
	}

	@Override
	public String getType() {
		return "file";
	}

	@Override
	public void writeLine(String line) {
		try {
			// Append string with new line to file.
			writer.append(line + LINE_SEPARATOR);
			writer.flush();
		} catch (IOException e) {
			System.out.println("An error occurred while writing to file " + path + ".");
			e.printStackTrace();
		}
	}

	@Override
	public void writeAllLines(List<String> allLines) {
		initializeFileWriter(true);

		try {
			for (String line : allLines) {
				writer.append(line + LINE_SEPARATOR);
			}
			close();
		} catch (IOException e) {
			System.out.println("An error occurred while writing to file " + path + ".");
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		try {
			// Close file.
			writer.close();
		} catch (IOException e) {
			System.out.println("An error occurred while closing file " + path + ".");
			e.printStackTrace();
		}
	}

	public void clearFile() {
		close();
		// Initialize file to overwrite contents.
		initializeFileWriter(false);
		// Write empty string to file.
		writeLine("");
	}

}
