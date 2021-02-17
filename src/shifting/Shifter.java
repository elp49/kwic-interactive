package shifting;

import java.util.List;

import kwic.Line;
import linestorage.LineRetrievable;

public abstract class Shifter implements LineRetrievable {

	protected LineRetrievable storage = null;

	public Shifter(LineRetrievable storage) {
		this.storage = storage;
	}

	public abstract List<Line> getAllLines();

	public abstract void shift();

}
