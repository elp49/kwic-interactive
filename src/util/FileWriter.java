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
	}

	@Override
	public String path() {
		return path;
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
		initializeFileWriter(true);

		_writeLine(line);

		close();
	}

	@Override
	public void writeAllLines(List<String> allLines) {
		initializeFileWriter(true);

		for (String line : allLines) {
			_writeLine(line);
		}

		close();
	}

	private void _writeLine(String line) {
		try {
			// Append string with new line to file.
			writer.append(line + LINE_SEPARATOR);
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

		writer = null;
	}

	public void clearFile() {
		// Test if file writer is not null.
		if (writer != null) {
			close();
		}

		// Initialize file to overwrite contents.
		initializeFileWriter(false);

		try {
			// Overwrite file contents.
			writer.write("");
			close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
