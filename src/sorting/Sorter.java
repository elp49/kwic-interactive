package sorting;

import java.util.List;

import kwic.Line;
import linestorage.LineRetrievable;
import shifting.Shifter;

public abstract class Sorter implements LineRetrievable {

	protected Shifter shifter = null;

	public Sorter(Shifter shifter) {
		this.shifter = shifter;
	}

	public abstract List<Line> getAllLines();

	public abstract void sort();

}
