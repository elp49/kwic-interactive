package configuration;

public class LineInputPreferences extends Preferences {

	public LineInputPreferences(ConfigurationProcessor config) {
		super(config);
	}

	@Override
	public String getType() {
		return "line input";
	}

	@Override
	protected String getMethodKey() {
		return "LINE_INPUT_METHOD";
	}

	@Override
	protected String getFilePathKey() {
		return "LINE_INPUT_FILE";
	}

	@Override
	protected String[] getMethodValues() {
		return new String[] { CONSOLE, FILE };
	}

	@Override
	protected String getDefaultMethod() {
		return CONSOLE;
	}

	@Override
	protected String getDefaultFilePath() {
		return "data_input.txt";
	}

}
