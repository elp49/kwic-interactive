package configuration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.naming.ConfigurationException;

public class ConfigurationProcessor {

	private Properties props = null;

	public ConfigurationProcessor() {
		props = new Properties();
	}

	public void loadConfigFile(String config) throws FileNotFoundException, Exception {
		// Load config file into properties object.
		FileReader reader = null;

		// Initialize File Reader object.
		try {
			reader = new FileReader(config);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Configuration file not found.");
		}

		// Load property values from File Reader.
		try {
			props.load(reader);
		} catch (Exception e) {
			throw new IOException("Error while reading configuration file. ".concat(e.toString()));
		}
	}

	public String getProperty(String key) throws ConfigurationException {
		if (!props.containsKey(key)) {
			throw new ConfigurationException("Configuration file does not contain the key \"" + key + "\".");
		}

		return props.getProperty(key);
	}

	public Object setProperty(String key, String value) {
		return props.setProperty(key, value);
	}

}
