package configuration;

import util.ConsoleReader;
import util.ConsoleWriter;
import util.FileReader;
import util.FileWriter;
import util.Readable;
import util.Writable;

public class IoProcessorFactory {

	public IoProcessorFactory() {
		// Intentionally empty.
	}

	public Readable getReader(IoPreferences preferences) {
		Readable reader = null;

		// Set reader based on IO preferences.
		String method = preferences.getInputMethod(); 
		if (method.equalsIgnoreCase(IoPreferences.CONSOLE)) {
			reader = new ConsoleReader();
		} else if (method.equalsIgnoreCase(IoPreferences.FILE)) {
			reader = new FileReader(preferences.getInputFilePath());
		}

		return reader;
	}

	public Writable getWriter(IoPreferences preferences) {
		Writable writer = null;

		// Set writer based on IO preferences.
		String method = preferences.getOutputMethod(); 
		if (method.equalsIgnoreCase(IoPreferences.CONSOLE)) {
			writer = new ConsoleWriter();
		} else if (method.equalsIgnoreCase(IoPreferences.FILE)) {
			writer = new FileWriter(preferences.getOutputFilePath());
		}

		return writer;
	}

}
