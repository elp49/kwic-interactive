package lineio;

import configuration.Preferences;
import util.ConsoleWriter;
import util.FileWriter;
import util.Writable;

public class OutputProcessorFactory {

	public OutputProcessorFactory() {
		// Intentionally empty.
	}

	public Writable getWriter(Preferences preferences) {
		Writable writer = null;

		// Set writer based on output preferences.
		String method = preferences.getMethod();
		if (method.equalsIgnoreCase(Preferences.CONSOLE)) {
			writer = new ConsoleWriter();
		} else if (method.equalsIgnoreCase(Preferences.FILE)) {
			writer = new FileWriter(preferences.getFilePath());
		}

		return writer;
	}

}
