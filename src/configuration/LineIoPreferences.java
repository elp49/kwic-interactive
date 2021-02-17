package configuration;

public class LineIoPreferences extends IoPreferences {

	@Override
	public String getType() {
		return "line";
	}


	public LineIoPreferences(ConfigurationProcessor config) {
		super(config);
	}

	@Override
	protected String getInputMethodKey() {
		return "LINE_INPUT_METHOD";
	}

	@Override
	protected String getInputFileKey() {
		return "LINE_INPUT_FILE";
	}

	@Override
	protected String[] getInputMethodValues() {
		return new String[] { CONSOLE, FILE };
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
		return "LINE_OUTPUT_METHOD";
	}

	@Override
	protected String getOutputFileKey() {
		return "LINE_OUTPUT_FILE";
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
