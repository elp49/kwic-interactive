package configuration;

public class UserOutputPreferences extends Preferences {

	public UserOutputPreferences(ConfigurationProcessor config) {
		super(config);
	}

	@Override
	public String getType() {
		return "user output";
	}

	@Override
	protected String getMethodKey() {
		return "USER_OUTPUT_METHOD";
	}

	@Override
	protected String getFilePathKey() {
		return "USER_OUTPUT_FILE";
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
		return "user_output.log";
	}

}
