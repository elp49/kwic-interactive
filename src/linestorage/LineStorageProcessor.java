package linestorage;

import java.util.List;

import kwic.Line;

public abstract class LineStorageProcessor implements LineStorable, LineRetrievable {

	public static final String CORE = "CORE";
	public static final String FILE = "FILE";
	
	public abstract String getType();

	@Override
	public abstract void addLine(Line line);

	@Override
	public abstract List<Line> getAllLines();
	
	public abstract void clear();

}
