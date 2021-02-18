package sorting;

import java.util.ArrayList;
import java.util.List;

import kwic.Line;
import shifting.Shifter;

public class AlphabeticalSorter extends Sorter {

	private List<Line> allSortedLines = null;

	public AlphabeticalSorter(Shifter shifter) {
		super(shifter);
		allSortedLines = new ArrayList<>();
	}

	@Override
	public List<Line> getAllLines() {
		return allSortedLines;
	}

	@Override
	public void sort() {
		List<Line> allLines = shifter.getAllLines();
		List<Line> sortedLines = getSortedLineList(allLines);
		allSortedLines.addAll(sortedLines);
	}

	private List<Line> getSortedLineList(List<Line> unsortedLines) {
		List<Line> sortedLines = new ArrayList<>();

		// Test if unsorted line list is not null or empty.
		if (unsortedLines != null && !unsortedLines.isEmpty()) {
			// Add first line to empty sorted line list.
			sortedLines.add(unsortedLines.get(0));

			// Add the rest of the lines in their approproate positions.
			for (int i = 1; i < unsortedLines.size(); i++) {
				Line line = unsortedLines.get(i);
				int index = getSortedLineIndex(sortedLines, line);
				sortedLines.add(index, line);
			}
		}

		return sortedLines;
	}

	private int getSortedLineIndex(List<Line> sortedLines, Line unsortedLine) {
		int index = 0;

		// Get the line string and remove white space.
		String unsortedLineWithoutSpaces = getLineStringWithoutSpaces(unsortedLine);
		boolean isSorted = false;

		for (int i = 0; i < sortedLines.size(); i++) {
			// Get the next line from sorted line list.
			Line sortedLine = sortedLines.get(i);
			String sortedLineWithoutSpaces = getLineStringWithoutSpaces(sortedLine);

			// Compare the two lines strings.
			if (lineIsLessThanOrEqualTo(unsortedLineWithoutSpaces, sortedLineWithoutSpaces)) {
				// Record the current index and exit loop.
				index = i;
				isSorted = true;
				break;
			}
		}

		// Test if line was not sorted.
		if (!isSorted) {
			index = sortedLines.size();
		}

		return index;
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
