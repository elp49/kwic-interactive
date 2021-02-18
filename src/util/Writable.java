package util;

import java.util.List;

public interface Writable {

	public String path();

	public String getType();

	public void writeLine(String line);

	public void writeAllLines(List<String> lines);

	public void close();

}
