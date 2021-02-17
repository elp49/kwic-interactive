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
	public void addLine(Line line) {
		lines.add(line);
	}

	@Override
	public List<Line> getAllLines() {
		return lines;
	}

}
