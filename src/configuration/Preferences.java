package configuration;

import java.util.Arrays;

import javax.naming.ConfigurationException;

public abstract class Preferences {

	public static final String CONSOLE = "CONSOLE";
	public static final String FILE = "FILE";

	protected String method = "";
	protected String path = "";

	public Preferences(ConfigurationProcessor config) {
		initialize(config);
		validate();
	}

	public String getMethod() {
		return method;
	}

	public String getFilePath() {
		return path;
	}

	public void setDefaultPreferences() {
		method = getDefaultMethod();
		path = getDefaultFilePath();
	}
	
	public void setMethod(String method) {
		this.method = method;
		validate();
	}

	private void initialize(ConfigurationProcessor config) {
		initializeMethod(config);
		initializeFilePath(config);
	}

	private void initializeMethod(ConfigurationProcessor config) {
		try {
			// Set method from config props.
			method = config.getProperty(getMethodKey());
		} catch (ConfigurationException e) {
			method = getDefaultMethod();
		}
	}

	private void initializeFilePath(ConfigurationProcessor config) {
		try {
			// Set input file path from config props.
			path = config.getProperty(getFilePathKey());
		} catch (ConfigurationException e) {
			path = getDefaultFilePath();
		}
	}

	private void validate() {
		String reason = performMethodLegalityChecks();

		if (failedAnyLegalityChecks(reason)) {
			System.err.println("Failed to load " + getType() + " preference settings: " + reason);
			System.out.println("Setting " + getType() + " preferences to default.");

			setDefaultPreferences();
		}
	}

	private String performMethodLegalityChecks() {
		String result = "";

		if (!Arrays.asList(getMethodValues()).contains(method)) {
			result = "The " + getType() + " method value \"" + method + "\" is illegal. Legal " + getType()
					+ " method values include: " + String.join(", ", Arrays.asList(getMethodValues()));
		}

		return result;
	}

	private boolean failedAnyLegalityChecks(String reason) {
		return reason != null && !reason.trim().isEmpty();
	}

	public abstract String getType();

	protected abstract String getMethodKey();

	protected abstract String getFilePathKey();

	protected abstract String[] getMethodValues();

	protected abstract String getDefaultMethod();

	protected abstract String getDefaultFilePath();

}
