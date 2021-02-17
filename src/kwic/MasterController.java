/**
 * 
 */
package kwic;

import java.io.FileNotFoundException;

import configuration.ConfigurationProcessor;
import configuration.LineInputPreferences;
import configuration.LineOutputPreferences;
import configuration.Preferences;
import configuration.StoragePreferences;
import configuration.UserOutputPreferences;
import lineio.InputProcessorFactory;
import lineio.LineInputProcessor;
import lineio.LineOutputProcessor;
import lineio.OutputProcessorFactory;
import linestorage.LineStorageProcessor;
import linestorage.StorageFactory;
import shifting.CircularShifter;
import shifting.Shifter;
import sorting.AlphabeticalSorter;
import sorting.Sorter;
import util.ConsoleReader;
import util.Readable;
import util.Writable;

/**
 * @author Edward Parrish
 *
 */
public class MasterController {

	private String configFile = "config";
	private ConfigurationProcessor config = null;

	private UserCommandOperator user = null;
	private LineInputProcessor lineIn = null;
	private LineOutputProcessor lineOut = null;
	private LineStorageProcessor storage = null;
	private Shifter shifter = null;
	private Sorter sorter = null;

	/*
	 * @param args
	 */
	public static void main(String[] args) {
		MasterController master = new MasterController();
		master.run();
	}

	public void run() {
		initializeConfig();
		initialize();

		Preferences lineInputPreferences = new LineInputPreferences(config);

		String command = "";
		while (!(command = user.getUserCommand(UserCommandOperator.MAIN_COMMANDS))
				.equalsIgnoreCase(UserCommandOperator.QUIT)) {

			if (command.equalsIgnoreCase(UserCommandOperator.RUN_CONSOLE)) {
				lineInputPreferences.setMethod(Preferences.CONSOLE);
				run(lineInputPreferences);
			} else if (command.equalsIgnoreCase(UserCommandOperator.RUN_FILE)) {
				lineInputPreferences.setMethod(Preferences.FILE);
				run(lineInputPreferences);
			} else if (command.equalsIgnoreCase(UserCommandOperator.RUN_INTERACTIVE)) {
				runInteractiveMode();
			}

			initialize();
		}
	}

	private void run(Preferences lineInputPreferences) {
		// Initialize line input reader.
		InputProcessorFactory inputFactory = new InputProcessorFactory();
		Readable lineReader = inputFactory.getReader(lineInputPreferences);

		user.writeLine(" - Reading input from " + lineReader.getType() + ".");
		user.writeLine("");
		lineIn.parse(lineReader);

		user.writeLine(" - Shifting lines.");
		shifter.shift();
		user.writeLine("");

		user.writeLine(" - Sorting lines.");
		sorter.sort();
		user.writeLine("");

		user.writeLine(" - Writing output to " + lineOut.getType() + ".");
		lineOut.print();
		user.writeLine("");

		user.writeLine(" - Clearing line storage.");
		storage.clear();
		user.writeLine("");
	}

	private void runInteractiveMode() {
		String command = user.getUserCommand(UserCommandOperator.INTERACTIVE_COMMANDS);

		if (command.equalsIgnoreCase(UserCommandOperator.ADD)) {
		} else if (command.equalsIgnoreCase(UserCommandOperator.DELETE)) {
		} else if (command.equalsIgnoreCase(UserCommandOperator.PRINT)) {
		}
	}

	private void initializeConfig() {
		// Initialize config processor.
		config = new ConfigurationProcessor();

		try {
			// Load config file properties.
			config.loadConfigFile(configFile);
		} catch (FileNotFoundException e) {
			System.err.println(e.toString());
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	private void initialize() {
		OutputProcessorFactory outputFactory = new OutputProcessorFactory();
		initializeUserCommandOperator(outputFactory);
		initializeModules(outputFactory);
	}

	private void initializeUserCommandOperator(OutputProcessorFactory outputFactory) {
		Preferences userOutputPreferences = new UserOutputPreferences(config);
		Readable userIn = new ConsoleReader();
		Writable userOut = outputFactory.getWriter(userOutputPreferences);
		user = new UserCommandOperator(userIn, userOut);
	}

	private void initializeModules(OutputProcessorFactory outputFactory) {
		initializeLineStorage();
		// Preferences which determine console or file line output.
		Preferences lineOutputPreferences = new LineOutputPreferences(config);
		Writable lineWriter = outputFactory.getWriter(lineOutputPreferences);

		lineIn = new LineInputProcessor(storage);
		shifter = new CircularShifter(storage);
		sorter = new AlphabeticalSorter(shifter);
		lineOut = new LineOutputProcessor(lineWriter, sorter);
	}

	private void initializeLineStorage() {
		StoragePreferences storageIoPreferences = new StoragePreferences(config);
		StorageFactory storageFactory = new StorageFactory();
		storage = storageFactory.getLineStorageProcessor(storageIoPreferences);
	}

}
