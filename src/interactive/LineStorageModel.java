package interactive;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import kwic.Line;
import linestorage.LineStorageProcessor;

public class LineStorageModel {

	private ArrayList<ActionListener> actionListenerList = null;
	private LineStorageProcessor storage = null;

	public LineStorageModel(LineStorageProcessor storage) {
		this.storage = storage;
	}

	public LineStorageProcessor getLineStorage() {
		return storage;
	}

	public String getType() {
		return storage.getType();
	}

	public void addLine(Line line) {
		storage.addLine(line);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add line"));
	}

	public List<Line> getAllLines() {
		return storage.getAllLines();
	}

	public void clear() {
		storage.clear();
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "clear line storage"));
	}

	public void deleteLine(int index) {
		storage.remove(index);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "deleted line"));
	}

	public synchronized void addActionListener(ActionListener listener) {
		if (actionListenerList == null) {
			actionListenerList = new ArrayList<ActionListener>();
		}

		actionListenerList.add(listener);
	}

	public synchronized void removeActionListener(ActionListener listener) {
		if (actionListenerList != null && actionListenerList.contains(listener)) {
			actionListenerList.remove(listener);
		}
	}

	private void processEvent(ActionEvent e) {
		ArrayList<ActionListener> list;

		synchronized (this) {
			if (actionListenerList == null) {
				return;
			}

			list = (ArrayList<ActionListener>) actionListenerList.clone();
		}

		for (int i = 0; i < list.size(); i++) {
			ActionListener listener = list.get(i);
			listener.actionPerformed(e);
		}
	}

}
