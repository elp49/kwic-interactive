package util;

import java.util.List;

public class ConsoleWriter implements Writable {

	public ConsoleWriter() {
	}

	@Override
	public String path() {
		return "console";
	}

	@Override
	public String getType() {
		return "console";
	}

	@Override
	public void writeLine(String line) {
		System.out.println(line);
	}

	@Override
	public void writeAllLines(List<String> allLines) {
		for (String line : allLines) {
			System.out.println(line);
		}
	}

	@Override
	public void close() {
		// Intentionally empty.
	}

}
