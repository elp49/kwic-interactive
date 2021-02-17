package lineio;

import kwic.Line;
import linestorage.LineStorable;
import util.Readable;

public class LineInputProcessor {

	private static final String SPECIAL_CHAR_REGEX = "[^ a-zA-Z0-9]";

	private LineStorable storage = null;

	public LineInputProcessor(LineStorable storage) {
		this.storage = storage;
	}

	public void parse(Readable reader) {
		// Read line from reader.
		String line = reader.readLine();

		while (line != null) {
			line = removeAllSpecialCharacters(line);
			line = removeDuplicateWhiteSpace(line);

			// Add line to storage.
			storage.addLine(new Line(line));
			// Read next line from reader.
			line = reader.readLine();
		}
	}

	private String removeAllSpecialCharacters(String line) {
		return line.replaceAll(SPECIAL_CHAR_REGEX, "");
	}

	private String removeDuplicateWhiteSpace(String line) {
		return line.trim().replaceAll(" +", " ");
	}

}
