package shifting;

import java.util.ArrayList;
import java.util.List;

import kwic.Line;
import linestorage.LineRetrievable;

public class CircularShifter extends Shifter {

	private List<Line> allShiftedLines = null;

	public CircularShifter(LineRetrievable storage) {
		super(storage);
		allShiftedLines = new ArrayList<>();
	}

	@Override
	public List<Line> getAllLines() {
		return allShiftedLines;
	}

	public void shift() {
		for (Line line : storage.getAllLines()) {
			List<Line> shiftedLines = getShiftedLineList(line);
			allShiftedLines.addAll(shiftedLines);
		}
	}

	private List<Line> getShiftedLineList(Line line) {
		List<Line> shiftedLines = new ArrayList<>();

		line = new Line(line);
		for (int i = 0; i < line.size(); i++) {
			// Add line to shifted line list.
			shiftedLines.add(line);

			// Shift line.
			line = getShiftedLine(line);
		}

		return shiftedLines;
	}

	private Line getShiftedLine(Line line) {
		Line shiftedLine = new Line(line);
		String firstWord = shiftedLine.remove(0);

		shiftedLine.add(firstWord);
		return shiftedLine;
	}

}
