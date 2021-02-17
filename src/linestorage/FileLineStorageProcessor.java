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
	public void addLine(Line line) {
		writer.writeLine(line.toString());
	}

	@Override
	public List<Line> getAllLines() {
		List<Line> allLines = new ArrayList<Line>();

		// Create Line objects for each line string.
		for (String line : reader.readAllLines()) {
			allLines.add(new Line(line));
		}

		return allLines;
	}

}
