package kwic;

import util.Readable;
import util.Writable;

public class UserCommandOperator {

	private Readable in = null;
	private Writable out = null;

	public static final String RUN = "Run";
	public static final String QUIT = "Quit";
	public static final String[] MAIN_COMMANDS = { RUN, QUIT, };

	public static final String ADD = "Add";
	public static final String DELETE = "Delete";
	public static final String PRINT = "Print";
	public static final String[] INTERACTIVE_COMMANDS = { ADD, DELETE, PRINT, QUIT, };

	public String getInputType() {
		return in.getType();
	}

	public String getOutputType() {
		return out.getType();
	}

	public UserCommandOperator(Readable in, Writable out) {
		this.in = in;
		this.out = out;
	}

	public void writeLine(String line) {
		out.writeLine(line);
	}

	public String getUserCommand(String[] commands) {
		// Test if commands is null or empty.
		if (commands == null || commands.length == 0) {
			return "";
		}

		// Write user command options.
		displayCommands(commands);

		// Get user command.
		Integer index = readInteger();
		while (index == null || index < 1 || index > commands.length) {
			out.writeLine("Invalid command. Enter a value between 1 and " + commands.length + ".");
			index = readInteger();
		}

		return commands[index - 1];
	}

	private void displayCommands(String[] commands) {
		// Test if commands is null or empty.
		if (commands == null || commands.length == 0) {
			return;
		}

		// Write command prompt.
		out.writeLine("Select a command (enter the number): ");

		// Write commands.
		for (int i = 1; i <= commands.length; i++) {
			out.writeLine(i + ". " + commands[i - 1]);
		}
	}

	private Integer readInteger() {
		Integer result = null;
		// Read integer.
		String input = (String) in.readLine();

		try {
			// Parse integer from input string.
			result = Integer.parseInt(input);
		} catch (NumberFormatException ignore) {
		}

		return result;
	}

}
