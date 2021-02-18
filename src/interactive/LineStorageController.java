package interactive;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import kwic.Line;
import lineio.InputFormatter;

public class LineStorageController {

	private LineStorageModel storageModel = null;
	private LineInputWindow inputView = null;

	public LineStorageController() {
		// intentionally empty.
	}

	private class AddLineListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// Get formatted line.
			LineInputWindow inputView = getInputWindow();
			String line = inputView.lineTextField.getText();
			InputFormatter formatter = new InputFormatter();
			line = formatter.formatLine(line);

			// Add line to storage.
			LineStorageModel model = getModel();
			model.addLine(new Line(line));
			inputView.deleteLineComboBox.addItem(line);
		}

	}

	private class ClearLineStorageListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LineStorageModel model = getModel();
			model.clear();
			inputView.deleteLineComboBox.removeAllItems();
		}

	}

	private class ClearInputFieldsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LineInputWindow inputView = getInputWindow();
			inputView.lineTextField.setText(null);
		}

	}

	private class DeleteLineListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LineInputWindow inputView = getInputWindow();
			int index = inputView.deleteLineComboBox.getSelectedIndex();

			// Test if index is greater than or equal to 0.
			if (index >= 0) {
				// Delete line from storage.
				LineStorageModel model = getModel();
				model.deleteLine(index);
				inputView.deleteLineComboBox.removeItemAt(index);
			}
		}

	}

	public void setModel(LineStorageModel storageModel) {
		this.storageModel = storageModel;
	}

	public LineStorageModel getModel() {
		return storageModel;
	}

	public void setInputWindow(LineInputWindow theView) {
		inputView = theView;

		// Register listeners.
		inputView.getAddLineButton().addActionListener(new AddLineListener());
		inputView.getClearStorageButton().addActionListener(new ClearLineStorageListener());
		inputView.getClearInputButton().addActionListener(new ClearInputFieldsListener());
		inputView.getDeleteLineButton().addActionListener(new DeleteLineListener());
	}

	public LineInputWindow getInputWindow() {
		return inputView;
	}

}
