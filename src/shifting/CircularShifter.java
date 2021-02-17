package shifting;

import java.util.ArrayList;
import java.util.List;

import kwic.Line;
import linestorage.LineRetrievable;

public class CircularShifter extends Shifter {

	private List<Line> shiftedLines = null;

	public CircularShifter(LineRetrievable storage) {
		super(storage);
		shiftedLines = new ArrayList<>();
	}

	@Override
	public List<Line> getAllLines() {
		return shiftedLines;
	}

	@Override
	public void shift() {
		List<Line> allLines = storage.getAllLines();

		// Test if storage is not null or empty.
		if (allLines != null && !allLines.isEmpty()) {
			for (Line line: allLines) {
				shiftLine(line);
			}
		}
	}
	
	private void shiftLine(Line line) {
		// Add first line to shifted line list.
		Line shiftedLine = new Line(line);
		shiftedLines.add(shiftedLine);

		// Test if line has more than one word.
		int numWords = shiftedLine.size();
		if (numWords > 1) {
			for (int i = 1; i < numWords; i++) {
				// Get the first word.
				String firstWord = shiftedLine.get(0);
				// Get the rest of the words.
				shiftedLine = shiftedLine.subList(1, shiftedLine.size());
				// Append first word to the end of list.
				shiftedLine.add(firstWord);
				// Append shifted line to list.
				shiftedLines.add(shiftedLine);
			}
		}
	}

}
