package lineio;

import kwic.Line;
import linestorage.LineStorable;
import util.Readable;

public class LineInputProcessor {

	private LineStorable storage = null;
	private InputFormatter formatter = null;

	public LineInputProcessor(LineStorable storage) {
		this.storage = storage;
		formatter = new InputFormatter();
	}

	public void parse(Readable reader) {
		for (String line : reader.readAllLines()) {
			// Format line.
			line = formatter.formatLine(line);

			// Add formatted line to storage.
			storage.addLine(new Line(line));
		}
	}

}
