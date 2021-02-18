package lineio;

public class InputFormatter {

	private static final String SPECIAL_CHAR_REGEX = "[^ a-zA-Z0-9]";

	public InputFormatter() {
		// Intentionally empty.
	}

	public String formatLine(String line) {
		line = removeAllSpecialCharacters(line);
		line = removeDuplicateWhiteSpace(line);
		return line;
	}

	private String removeAllSpecialCharacters(String line) {
		return line.replaceAll(SPECIAL_CHAR_REGEX, "");
	}

	private String removeDuplicateWhiteSpace(String line) {
		return line.trim().replaceAll(" +", " ");
	}
}
