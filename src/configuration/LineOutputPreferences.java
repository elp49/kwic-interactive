package configuration;

public class LineOutputPreferences extends Preferences {

	public LineOutputPreferences(ConfigurationProcessor config) {
		super(config);
	}

	@Override
	public String getType() {
		return "line output";
	}

	@Override
	protected String getMethodKey() {
		return "LINE_OUTPUT_METHOD";
	}

	@Override
	protected String getFilePathKey() {
		return "LINE_OUTPUT_FILE";
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
		return "data_output.txt";
	}
}
