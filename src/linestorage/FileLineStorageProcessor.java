package linestorage;

import java.util.ArrayList;
import java.util.List;

import kwic.Line;
import util.FileReader;
import util.FileWriter;

public class FileLineStorageProcessor extends LineStorageProcessor {

	private FileReader reader = null;
	private FileWriter writer = null;

	public FileLineStorageProcessor(FileReader reader, FileWriter writer) {
		this.reader = reader;
		this.writer = writer;
	}

	@Override
	public String getType() {
		return FILE;
	}

	@Override
	public void addLine(Line line) {
		// Test if line is not null, empty or blank.
		if (line != null && !line.isEmpty() && !line.isBlank()) {
			writer.writeLine(line.toString());
		}
	}

	@Override
	public List<Line> getAllLines() {
		List<Line> allLines = new ArrayList<Line>();

		// Create Line objects for each line string.
		String line = reader.readLine();
		while (line != null) {
			allLines.add(new Line(line));
			line = reader.readLine();
		}

		return allLines;
	}

	@Override
	public void clear() {
		writer.clearFile();
	}

}
