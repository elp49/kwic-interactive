package linestorage;

import java.util.List;

import kwic.Line;

public abstract class LineStorageProcessor implements LineStorable, LineRetrievable {
	
	@Override
	public abstract void addLine(Line line);

	@Override
	public abstract List<Line> getAllLines();

}
