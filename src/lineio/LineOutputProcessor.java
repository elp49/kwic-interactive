package lineio;

import java.util.List;

import kwic.Line;
import linestorage.LineRetrievable;
import util.Writable;

public class LineOutputProcessor {

	private Writable writer = null;
	private LineRetrievable storage = null;

	public String getType() {
		return writer.getType();
	}

	public LineOutputProcessor(Writable writer, LineRetrievable storage) {
		this.writer = writer;
		this.storage = storage;
	}

	public void print() {
		// Get line from storage.
		List<Line> allLines = storage.getAllLines();

		for (String line : Line.toString(allLines)) {
			writer.writeLine(line);
		}
	}

}
