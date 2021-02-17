package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleReader implements Readable {

	private BufferedReader reader = null;

	public ConsoleReader() {
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	@Override
	public String getType() {
		return "console";
	}

	@Override
	public String readLine() {
		String line = null;

		try {
			// Read line from console.
			line = reader.readLine().trim();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return line;
	}

	@Override
	public List<String> readAllLines() {
		List<String> allLines = new ArrayList<String>();
		
		String line = readLine();
		
		while (line != null) {
			allLines.add(line);
			line = readLine();
		}

		return allLines;
	}

}
