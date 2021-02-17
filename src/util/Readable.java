package util;

import java.util.List;

public interface Readable {

	public String getType();

	public String readLine();

	public List<String> readAllLines();
	
}
