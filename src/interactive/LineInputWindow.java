package interactive;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import linestorage.LineStorageProcessor;

public class LineInputWindow extends JFrame {

	private static final long serialVersionUID = 21305503976948004L;

	JButton addLineBtn = new JButton("Add Line");

	JButton clearInputBtn = new JButton("Clear Line Input");

	JButton clearStorageBtn = new JButton("Clear Line Storage");

	JButton deleteLineBtn = new JButton("Delete Selected Line");

	JTextField lineTextField = new JTextField(50);
	JLabel lineLable = new JLabel("Line", JLabel.RIGHT);

	JComboBox<String> deleteLineComboBox = null;
	JLabel deleteLineLable = new JLabel("Pick a line to delete", JLabel.RIGHT);

	public LineInputWindow(LineStorageProcessor storage) {
		setTitle("Add Lines to the Line Storage");

		JPanel lineInputPanel = new JPanel(new GridLayout(1, 0, 5, 5));
		lineInputPanel.add(lineLable);
		lineInputPanel.add(lineTextField);

		deleteLineComboBox = new JComboBox<String>(storage.asArray());
		JPanel deleteLinePanel = new JPanel(new GridLayout(1, 0, 5, 5));
		deleteLinePanel.add(deleteLineLable);
		deleteLinePanel.add(deleteLineComboBox);

		JPanel buttonPanel = new JPanel(new GridLayout(1, 0, 5, 5));
		buttonPanel.add(addLineBtn);
		buttonPanel.add(clearInputBtn);
		buttonPanel.add(clearStorageBtn);
		buttonPanel.add(deleteLineBtn);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(0, 1, 5, 5));
		add(lineInputPanel);
		add(deleteLinePanel);
		add(buttonPanel);

		pack();
		setVisible(true);
	}

	public JButton getAddLineButton() {
		return addLineBtn;
	}

	public JButton getClearInputButton() {
		return clearInputBtn;
	}

	public JButton getClearStorageButton() {
		return clearStorageBtn;
	}

	public JButton getDeleteLineButton() {
		return deleteLineBtn;
	}

	public void clearInputFields() {
		lineTextField.setText("");
		deleteLineComboBox.setSelectedIndex(0);
	}

}
