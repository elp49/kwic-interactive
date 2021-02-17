/**
 * 
 */
package kwic;

import java.io.FileNotFoundException;

import configuration.ConfigurationProcessor;
import configuration.IoPreferences;
import configuration.IoProcessorFactory;
import configuration.LineIoPreferences;
import configuration.StorageFactory;
import configuration.StoragePreferences;
import configuration.UserIoPreferences;
import lineio.LineInputProcessor;
import lineio.LineOutputProcessor;
import linestorage.LineStorageProcessor;
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
		initialize();

		String command = user.getUserCommand(UserCommandOperator.MAIN_COMMANDS);

		if (command.equalsIgnoreCase(UserCommandOperator.RUN)) {
			readLineInput();
			shiftLines();
			sortLines();
			printLineOutput();

			runInteractiveMode();
		}
	}

	private void runInteractiveMode() {
		String command = user.getUserCommand(UserCommandOperator.INTERACTIVE_COMMANDS);

		if (command.equalsIgnoreCase(UserCommandOperator.ADD)) {
		} else if (command.equalsIgnoreCase(UserCommandOperator.DELETE)) {
		} else if (command.equalsIgnoreCase(UserCommandOperator.PRINT)) {
		}
	}

	private void readLineInput() {
		user.writeLine("Reading input from " + lineIn.getType() + ".");
		lineIn.parse();
	}

	private void shiftLines() {
		user.writeLine("Shifting lines.");
		shifter.shift();
	}

	private void sortLines() {
		user.writeLine("Sorting lines.");
		sorter.sort();
	}

	private void printLineOutput() {
		user.writeLine("Writing output to " + lineOut.getType() + ".");
		lineOut.print();
	}

	private void initialize() {
		initializeConfig();
		IoProcessorFactory ioFactory = new IoProcessorFactory();
		initializeUserCommandOperator(ioFactory);
		initializeLineStorage();
		initializeModules(ioFactory);
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

	private void initializeUserCommandOperator(IoProcessorFactory ioFactory) {
		IoPreferences userIoPreferences = new UserIoPreferences(config);
		Readable userIn = new ConsoleReader();
		Writable userOut = ioFactory.getWriter(userIoPreferences);
		user = new UserCommandOperator(userIn, userOut);
	}

	private void initializeLineStorage() {
		StoragePreferences storageIoPreferences = new StoragePreferences(config);
		StorageFactory storageFactory = new StorageFactory();
		storage = storageFactory.getLineStorageProcessor(storageIoPreferences);
	}

	private void initializeModules(IoProcessorFactory ioFactory) {
		IoPreferences lineIoPreferences = new LineIoPreferences(config);

		Readable lineReader = ioFactory.getReader(lineIoPreferences);
		Writable lineWriter = ioFactory.getWriter(lineIoPreferences);

		lineIn = new LineInputProcessor(lineReader, storage);
		shifter = new CircularShifter(storage);
		sorter = new AlphabeticalSorter(shifter);
		lineOut = new LineOutputProcessor(lineWriter, sorter);
	}

}
