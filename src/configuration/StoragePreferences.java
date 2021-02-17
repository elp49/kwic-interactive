package configuration;

import java.util.Arrays;

import javax.naming.ConfigurationException;

public class StoragePreferences extends Preferences {

	public static final String FILE = "FILE";
	public static final String CORE = "CORE";
	
	protected String storageMethod = "";
	protected String storageFilePath = "";

	public StoragePreferences(ConfigurationProcessor config) {
		super();
		initialize(config);
		validate();
	}

	@Override
	public String getType() {
		return "storage";
	}

	public String getStorageMethod() {
		return storageMethod;
	}

	public String getStorageFilePath() {
		return storageFilePath;
	}

	protected String getStorageMethodKey() {
		return "LINE_STORAGE_METHOD";
	}

	protected String getStorageFileKey() {
		return "LINE_STORAGE_FILE";
	}

	protected String[] getStorageMethodValues() {
		return new String[] { CORE, FILE };
	}
	
	protected String getDefaultStorageMethod() {
		return CORE;
	}

	protected String getDefaultStorageFile() {
		return "storage.txt";
	}

	public void setDefaultStoragePreferences() {
		storageMethod = getDefaultStorageMethod();
		storageFilePath = getDefaultStorageFile();
	}

	@Override
	protected void initialize(ConfigurationProcessor config) {
		try {
			// Set storage method from config props.
			storageMethod = config.getProperty(getStorageMethodKey());
		} catch (ConfigurationException e) {
			storageMethod = getDefaultStorageMethod();
		}

		try {
			// Set storage file path from config props.
			storageFilePath = config.getProperty(getStorageFileKey());
		} catch (ConfigurationException e) {
			storageFilePath = getDefaultStorageFile();
		}
	}

	@Override
	protected void validate() {
		// Perform legality checks on storage preferences.
		String reason = performStorageMethodLegalityChecks();

		// Test if failed any legality check.
		if (reason != null && !reason.trim().isEmpty()) {
			System.err.println("Failed to load " + getType() + " storage preference settings: " + reason);
			System.out.println("Setting " + getType() + " storage preferences to default.");

			// Set storage preferences to default.
			setDefaultStoragePreferences();
		}
	}

	private String performStorageMethodLegalityChecks() {
		String result = "";

		if (!Arrays.asList(getStorageMethodValues()).contains(storageMethod)) {
			result = "The " + getType() + " storage method value \"" + storageMethod + "\" is illegal. Legal " + getType()
					+ " storage method values include: " + String.join(", ", Arrays.asList(getStorageMethodValues()));
		}

		return result;
	}

}
