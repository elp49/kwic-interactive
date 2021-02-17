package lineio;

import java.util.List;

import kwic.Line;
import linestorage.LineStorable;
import util.Readable;

public class LineInputProcessor {

	private static final String SPECIAL_CHAR_REGEX = "[^ a-zA-Z0-9]";

	private Readable reader = null;
	private LineStorable storage = null;

	public String getType() {
		return reader.getType();
	}

	public LineInputProcessor(Readable reader, LineStorable storage) {
		this.reader = reader;
		this.storage = storage;
	}

	public void parse() {
		// Read line from reader.
		List<String> lines = reader.readAllLines();

		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			// Remove all special characters.
			line = line.replaceAll(SPECIAL_CHAR_REGEX, "");

			// Remove duplicate whitespace.
			line = line.trim().replaceAll(" +", " ");

			// Add line to storage.
			storage.addLine(new Line(line));
		}
	}

}
