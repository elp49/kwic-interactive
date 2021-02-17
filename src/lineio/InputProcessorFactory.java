package lineio;

import configuration.Preferences;
import util.ConsoleReader;
import util.FileReader;
import util.Readable;

public class InputProcessorFactory {

	public InputProcessorFactory() {
		// Intentionally empty.
	}

	public Readable getReader(Preferences preferences) {
		Readable reader = null;

		// Set reader based on IO preferences.
		String method = preferences.getMethod();
		if (method.equalsIgnoreCase(Preferences.CONSOLE)) {
			reader = new ConsoleReader();
		} else if (method.equalsIgnoreCase(Preferences.FILE)) {
			reader = new FileReader(preferences.getFilePath());
		}

		return reader;
	}

}
