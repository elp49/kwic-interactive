package configuration;

public class UserIoPreferences extends IoPreferences {

	@Override
	public String getType() {
		return "line";
	}

	public UserIoPreferences(ConfigurationProcessor config) {
		super(config);
	}

	@Override
	protected String getInputMethodKey() {
		return "USER_INPUT_METHOD";
	}

	@Override
	protected String getInputFileKey() {
		return "USER_INPUT_FILE";
	}

	@Override
	protected String[] getInputMethodValues() {
		return new String[] { CONSOLE };
	}

	@Override
	protected String getDefaultInputMethod() {
		return CONSOLE;
	}

	@Override
	protected String getDefaultInputFile() {
		return "";
	}

	@Override
	protected String getOutputMethodKey() {
		return "USER_OUTPUT_METHOD";
	}

	@Override
	protected String getOutputFileKey() {
		return "USER_OUTPUT_FILE";
	}

	@Override
	protected String[] getOutputMethodValues() {
		return new String[] { CONSOLE, FILE };
	}

	@Override
	protected String getDefaultOutputMethod() {
		return CONSOLE;
	}

	@Override
	protected String getDefaultOutputFile() {
		return "";
	}

}
