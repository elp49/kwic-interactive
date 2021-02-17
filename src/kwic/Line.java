package kwic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Line {
	
	public static final String WORD_SEPARATOR = " ";

	private List<String> words = null;

	public Line(String line) {
		words = new ArrayList<String>(Arrays.asList(new String(line).split(WORD_SEPARATOR)));
	}

	public Line(List<String> words) {
		this.words = new ArrayList<String>(words);
	}

	public Line(Line line) {
		this.words = new ArrayList<String>(line.words);
	}

	public String toString() {
		return String.join(WORD_SEPARATOR, words);
	}

	public static List<String> toString(List<Line> lines) {
		List<String> lineStrings = new ArrayList<>();

		for (Line l : lines) {
			lineStrings.add(l.toString());
		}

		return lineStrings;
	}

	public int size() {
		return words.size();
	}

	public String get(int i) {
		return words.get(i);
	}

	public Line subList(int i, int size) {
		return new Line(words.subList(i, size));
	}

	public void add(String word) {
		words.add(word);
	}
	
}
