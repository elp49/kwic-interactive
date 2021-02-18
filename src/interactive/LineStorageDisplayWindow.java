package interactive;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import kwic.Line;
import linestorage.LineStorageProcessor;
import shifting.CircularShifter;
import sorting.AlphabeticalSorter;

public class LineStorageDisplayWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = -7544472247240102278L;

	private JList lineListDisplay = null;
	private LineStorageModel model = null;

	public LineStorageDisplayWindow() {
		setTitle("Line Storage");
	}

	public void actionPerformed(ActionEvent e) {
		List<Line> lineList = getUpdateLineDisplayList(model.getAllLines());
		lineListDisplay.setListData(lineList.toArray());
		pack();
	}

	private List<Line> getUpdateLineDisplayList(List<Line> newLineList) {
		LineStorageProcessor storage = model.getLineStorage();
		CircularShifter shifter = new CircularShifter(storage);
		AlphabeticalSorter sorter = new AlphabeticalSorter(shifter);

		shifter.shift();
		sorter.sort();
		return sorter.getAllLines();
	}

	public void setModel(LineStorageModel model) {
		this.model = model;

		if (model != null) {
			model.addActionListener(this);
			add(new JLabel("Line"), BorderLayout.NORTH);

			// Add line list to display.
			List<Line> lineList = getUpdateLineDisplayList(model.getAllLines());
			lineListDisplay = new JList(lineList.toArray());
			add(lineListDisplay, BorderLayout.CENTER);

			pack();
			setVisible(true);
		}
	}

	public LineStorageModel getModel() {
		return model;
	}

}
