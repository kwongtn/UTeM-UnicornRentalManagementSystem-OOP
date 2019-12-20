package view.unicorn;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.manager.UnicornManager;
import controller.validator.MaximumLengthException;
import controller.validator.RequiredFieldException;
import controller.validator.Validator;

public class DeleteUnicornDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JTextField txtID = new JTextField();
	private JButton btnDelete = new JButton("Delete");
	private JButton btnReset = new JButton("Reset");

	public DeleteUnicornDialog(ManageUnicornDialog dialog) {
		super(dialog, "Delete Unicorn", true);

		JPanel pnlCentre = new JPanel(new GridLayout(3, 1, 10, 10));
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		pnlCentre.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		pnlSouth.setBorder(BorderFactory.createRaisedSoftBevelBorder());

		pnlCentre.add(new JLabel("Please enter the Unicorn ID that you want to delete.", JLabel.CENTER));
		pnlCentre.add(new JLabel("Unicorn ID: ", JLabel.LEFT));
		pnlCentre.add(txtID);

		pnlSouth.add(btnDelete);
		pnlSouth.add(btnReset);

		this.add(pnlCentre);
		this.add(pnlSouth, BorderLayout.SOUTH);

		btnDelete.addActionListener(this);
		btnReset.addActionListener(this);

		this.getRootPane().setDefaultButton(btnDelete);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(dialog);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == btnDelete) {
			Vector<Exception> exceptions = new Vector<>();
			int id = -1;

			try {
				id = Integer.parseInt(Validator.validate("Unicorn ID", txtID.getText(), true, 15));
			} catch (RequiredFieldException | MaximumLengthException | NumberFormatException e) {
				exceptions.add(e);
			}

			int size = exceptions.size();
			if (size == 0) {
				try {
					if (UnicornManager.deleteUnicorn(id) != 0) {
						JOptionPane.showMessageDialog(this, "Unicorn has been deleted.", "Success",
								JOptionPane.INFORMATION_MESSAGE);
						reset();
					} else
						JOptionPane.showMessageDialog(this, "Unable to delete Unicorn.", "Unsuccessful",
								JOptionPane.WARNING_MESSAGE);
				} catch (ClassNotFoundException | SQLException | NumberFormatException e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Database Error", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				String message = null;
				if (size == 1)
					message = exceptions.firstElement().getMessage();
				else {
					message = "PLease fix the following errors: ";

					for (int z = 0; z < size; z++)
						message += "\n" + (z + 1) + "." + exceptions.get(z).getMessage();
				}
				JOptionPane.showMessageDialog(this, message, "Validation Error", JOptionPane.WARNING_MESSAGE);
			}
			reset();
		} else if (source == btnReset) {
			reset();
		}
	}

	private void reset() {
		txtID.setText("");

	}

}