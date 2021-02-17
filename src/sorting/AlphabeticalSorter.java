package sorting;

import java.util.ArrayList;
import java.util.List;

import kwic.Line;
import shifting.Shifter;

public class AlphabeticalSorter extends Sorter {

	private List<Line> sortedLines = null;

	public AlphabeticalSorter(Shifter shifter) {
		super(shifter);
		sortedLines = new ArrayList<>();
	}

	@Override
	public List<Line> getAllLines() {
		return sortedLines;
	}

	@Override
	public void sort() {
		List<Line> allLines = shifter.getAllLines();

		// Test if line list is not null or empty.
		if (allLines != null && !allLines.isEmpty()) {
			// Remove first line and add it to sorted line list.
			sortedLines.add(allLines.remove(0));

			for (Line unsortedLine : allLines) {
				sortLine(unsortedLine);
			}
		}
	}

	private void sortLine(Line unsortedLine) {
		// Get the line sting and remove white space.
		String unsortedLineWithoutSpaces = getLineStringWithoutSpaces(unsortedLine);
		boolean isSorted = false;

		for (int i = 0; i < sortedLines.size(); i++) {
			// Get the next line from sorted line list.
			Line sortedLine = sortedLines.get(i);
			String sortedLineWithoutSpaces = getLineStringWithoutSpaces(sortedLine);

			// Compare the two lines strings.
			if (lineIsLessThanOrEqualTo(unsortedLineWithoutSpaces, sortedLineWithoutSpaces)) {
				// Insert line into sorted position.
				sortedLines.add(i, unsortedLine);
				isSorted = true;
				break;
			}
		}

		// Test if line was not sorted.
		if (!isSorted) {
			sortedLines.add(unsortedLine);
		}
	}

	private String getLineStringWithoutSpaces(Line line) {
		return line.toString().replaceAll(Line.WORD_SEPARATOR, "");
	}

	private boolean lineIsLessThanOrEqualTo(String l1, String l2) {
		int compare = l1.compareToIgnoreCase(l2);
		if (compare < 0 || compare == 0) {
			return true;
		}

		return false;
	}

}
