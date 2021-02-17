package linestorage;

import java.util.ArrayList;
import java.util.List;

import kwic.Line;

public class CoreLineStorageProcessor extends LineStorageProcessor {

	private List<Line> lines = null;

	public CoreLineStorageProcessor() {
		lines = new ArrayList<Line>();
	}

	@Override
	public String getType() {
		return CORE;
	}

	@Override
	public void addLine(Line line) {
		// Test if line is not null, empty or blank.
		if (line != null && !line.isEmpty() && !line.isBlank()) {
			lines.add(line);
		}
	}

	@Override
	public List<Line> getAllLines() {
		return lines;
	}

	@Override
	public void clear() {
		lines.clear();
	}

}
