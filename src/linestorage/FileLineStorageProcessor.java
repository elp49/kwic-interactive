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

		// Read all lines from file reader.
		for (String line : reader.readAllLines()) {
			allLines.add(new Line(line));
		}

		return allLines;
	}

	@Override
	public void clear() {
		writer.clearFile();
	}

	@Override
	public void remove(int index) {
		List<Line> allLines = getAllLines();
		allLines.remove(index);
		writer.clearFile();

		for (Line line : allLines) {
			addLine(line);
		}
	}

	@Override
	public String[] asArray() {
		List<Line> allLines = getAllLines();
		int numLines = allLines.size();
		String[] allLinesAsStrings = new String[numLines];

		for (int i = 0; i < numLines; i++) {
			String line = allLines.get(i).toString();
			allLinesAsStrings[i] = line;
		}

		return allLinesAsStrings;
	}

}
