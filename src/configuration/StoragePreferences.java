package configuration;

public class StoragePreferences extends Preferences {

	public static final String CORE = "CORE";

	public StoragePreferences(ConfigurationProcessor config) {
		super(config);
	}

	@Override
	public String getType() {
		return "storage";
	}

	@Override
	protected String getMethodKey() {
		return "LINE_STORAGE_METHOD";
	}

	@Override
	protected String getFilePathKey() {
		return "LINE_STORAGE_FILE";
	}

	@Override
	protected String[] getMethodValues() {
		return new String[] { CORE, FILE };
	}

	@Override
	protected String getDefaultMethod() {
		return CORE;
	}

	@Override
	protected String getDefaultFilePath() {
		return "storage.txt";
	}

}
