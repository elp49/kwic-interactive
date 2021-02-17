package configuration;

import java.util.Arrays;

import javax.naming.ConfigurationException;

public abstract class IoPreferences extends Preferences {

	public static final String CONSOLE = "CONSOLE";
	public static final String FILE = "FILE";

	protected String inputMethod = "";
	protected String inputFilePath = "";

	protected String outputMethod = "";
	protected String outputFilePath = "";

	public IoPreferences(ConfigurationProcessor config) {
		super();
		initialize(config);
		validate();
	}

	public String getInputMethod() {
		return inputMethod;
	}

	public String getInputFilePath() {
		return inputFilePath;
	}

	public String getOutputMethod() {
		return outputMethod;
	}

	public String getOutputFilePath() {
		return outputFilePath;
	}

	public void setDefaultInputPreferences() {
		inputMethod = getDefaultInputMethod();
		inputFilePath = getDefaultInputFile();
	}

	public void setDefaultOutputPreferences() {
		outputMethod = getDefaultOutputMethod();
		outputFilePath = getDefaultOutputFile();
	}

	protected void initialize(ConfigurationProcessor config) {
		initializeInputPreferences(config);
		initializeOutputPreferences(config);
	}

	private void initializeInputPreferences(ConfigurationProcessor config) {
		try {
			// Set input method from config props.
			inputMethod = config.getProperty(getInputMethodKey());
		} catch (ConfigurationException e) {
			inputMethod = getDefaultInputMethod();
		}

		try {
			// Set input file path from config props.
			inputFilePath = config.getProperty(getInputFileKey());
		} catch (ConfigurationException e) {
			inputFilePath = getDefaultInputFile();
		}
	}

	private void initializeOutputPreferences(ConfigurationProcessor config) {
		try {
			// Set output method from config props.
			outputMethod = config.getProperty(getOutputMethodKey());
		} catch (ConfigurationException e) {
			outputMethod = getDefaultOutputMethod();
		}

		try {
			// Set output file path from config props.
			outputFilePath = config.getProperty(getOutputFileKey());
		} catch (ConfigurationException e) {
			outputFilePath = getDefaultOutputFile();
		}
	}

	protected void validate() {
		validateInputPreferences();
		validateOutputPreferences();
	}

	private void validateInputPreferences() {
		// Perform legality checks on input preferences.
		String reason = performInputMethodLegalityChecks();

		// Test if failed any legality check.
		if (reason != null && !reason.trim().isEmpty()) {
			System.err.println("Failed to load " + getType() + " input preference settings: " + reason);
			System.out.println("Setting " + getType() + " input preferences to default.");

			// Set input preferences to default.
			setDefaultInputPreferences();
		}
	}

	private void validateOutputPreferences() {
		// Perform legality checks on output preferences.
		String reason = performOutputMethodLegalityChecks();

		// Test if failed any legality check.
		if (reason != null && !reason.trim().isEmpty()) {
			System.err.println("Failed to load " + getType() + " output preference settings: " + reason);
			System.out.println("Setting " + getType() + " output preferences to default.");

			// Set output preferences to default.
			setDefaultOutputPreferences();
		}
	}

	private String performInputMethodLegalityChecks() {
		String result = "";

		if (!Arrays.asList(getInputMethodValues()).contains(inputMethod)) {
			result = "The " + getType() + " input method value \"" + inputMethod + "\" is illegal. Legal " + getType()
					+ " input method values include: " + String.join(", ", Arrays.asList(getInputMethodValues()));
		}

		return result;
	}

	private String performOutputMethodLegalityChecks() {
		String result = "";

		if (!Arrays.asList(getOutputMethodValues()).contains(outputMethod)) {
			result = "The " + getType() + " output method value \"" + outputMethod + "\" is illegal. Legal " + getType()
					+ " output method values include: " + String.join(", ", Arrays.asList(getOutputMethodValues()));
		}

		return result;
	}

	public abstract String getType();

	protected abstract String getInputMethodKey();

	protected abstract String getInputFileKey();

	protected abstract String[] getInputMethodValues();

	protected abstract String getDefaultInputMethod();

	protected abstract String getDefaultInputFile();

	protected abstract String getOutputMethodKey();

	protected abstract String getOutputFileKey();

	protected abstract String[] getOutputMethodValues();

	protected abstract String getDefaultOutputMethod();

	protected abstract String getDefaultOutputFile();

}
