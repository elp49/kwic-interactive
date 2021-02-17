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

		try {
			File myObj = new File(path);
			myObj.createNewFile();
		} catch (IOException e) {
			System.err.println("An error occurred while creating file " + path + ".");
			e.printStackTrace();
		}

		try {
			// Initialize file writer.
			writer = new java.io.FileWriter(path, true);
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
	public void writeAllLines(List<String> lines) {
		try {
			// Initialize file writer.
			writer = new java.io.FileWriter(path, true);
			for (String l : lines) {
				// Append string with new line
				writer.append(l + System.lineSeparator());
			}
			writer.close();
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

}
