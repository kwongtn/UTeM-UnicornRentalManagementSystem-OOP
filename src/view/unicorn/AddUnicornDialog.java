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
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.manager.UnicornManager;
import controller.validator.InvalidNumberException;
import controller.validator.MaximumLengthException;
import controller.validator.MaximumNumberException;
import controller.validator.MinimumNumberException;
import controller.validator.RequiredFieldException;
import controller.validator.Validator;
import model.Unicorn;

public class AddUnicornDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JTextField txtUnicornID = new JTextField();
	private JTextField txtName = new JTextField(15);
	private JTextField txtType = new JTextField(15);
	private JTextField txtRate = new JTextField();
	private JTextField txtColor = new JTextField();
	private JCheckBox chkAvailable = new JCheckBox("Auto");
	private JCheckBox chkHeatlhCheck = new JCheckBox("Yes", true);
	private JButton btnSubmit = new JButton("Submit");
	private JButton btnReset = new JButton("Reset");

	public AddUnicornDialog(ManageUnicornDialog dialog) {
		super(dialog, "Add Unicorn", true);

		JPanel pnlCenter = new JPanel(new GridLayout(6, 2, 10, 10));
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		pnlSouth.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

		pnlCenter.add(new JLabel("Unicorn ID: ", JLabel.RIGHT));
		pnlCenter.add(txtUnicornID);
		pnlCenter.add(new JLabel("Name: ", JLabel.RIGHT));
		pnlCenter.add(txtName);
		pnlCenter.add(new JLabel("Type: ", JLabel.RIGHT));
		pnlCenter.add(txtType);
		pnlCenter.add(new JLabel("Rate (RM): ", JLabel.RIGHT));
		pnlCenter.add(txtRate);
		pnlCenter.add(new JLabel("Color: ", JLabel.RIGHT));
		pnlCenter.add(txtColor);
		pnlCenter.add(new JLabel("Is Unicorn Available?: ", JLabel.RIGHT));
		pnlCenter.add(chkAvailable);
		pnlCenter.add(new JLabel("Heatlh Checked?: ", JLabel.RIGHT));
		pnlCenter.add(chkHeatlhCheck);

		pnlSouth.add(btnSubmit);
		pnlSouth.add(btnReset);

		btnSubmit.addActionListener(this);
		btnReset.addActionListener(this);

		this.add(pnlCenter);
		this.add(pnlSouth, BorderLayout.SOUTH);

		this.getRootPane().setDefaultButton(btnSubmit);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(dialog);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();

		if (source == btnSubmit) {
			Vector<Exception> exceptions = new Vector<>();
			int unicornID = 0;
			String name = null, type = null, color = null;
			double rate = 0;

			try {
				unicornID = Validator.validate("Unicorn ID", txtUnicornID.getText(), true, true, true, 15, 4);
			} catch (RequiredFieldException | InvalidNumberException | MinimumNumberException | MaximumNumberException e) {
				exceptions.add(e);
			}

			try {
				name = Validator.validate("Name", txtName.getText(), true, 50);
			} catch (RequiredFieldException | MaximumLengthException e) {
				exceptions.add(e);
			}
			
			try {
				type = Validator.validate("Type", txtType.getText(), true, 50);
			} catch (RequiredFieldException | MaximumLengthException e) {
				exceptions.add(e);
			}

			try {
				rate = Validator.validate("Rate", txtRate.getText(), true, true, true, 5, 20);
			} catch (RequiredFieldException | InvalidNumberException | MinimumNumberException
					| MaximumNumberException e) {
				exceptions.add(e);
			}

			try {
				color = Validator.validate("Color", txtColor.getText(), true, 12);
			} catch (RequiredFieldException | MaximumLengthException e) {
				exceptions.add(e);
			}

			int size = exceptions.size();

			if (size == 0) {

				Unicorn unicorn = new Unicorn();

				unicorn.setUnicornID(unicornID);
				unicorn.setName(name);
				unicorn.setType(type);
				unicorn.setRate(rate);
				unicorn.setColor(color);
				unicorn.setAvailable(chkAvailable.isSelected());
				unicorn.setHealthCheck(chkHeatlhCheck.isSelected());

				try {
					if (UnicornManager.addUnicorn(unicorn) != -1) {
						JOptionPane.showMessageDialog(this,
								"Unicorn with ID " + unicorn.getUnicornID() + " has been succesfully added.", "Success",
								JOptionPane.INFORMATION_MESSAGE);
						reset();

					} else {
						JOptionPane.showMessageDialog(this, "Unable to add new Unicorn.", "Unsuccesful",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (ClassNotFoundException | SQLException e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Database Error", JOptionPane.WARNING_MESSAGE);
				}

			} else {
				String message = null;
				if (size == 1) {
					message = exceptions.firstElement().getMessage();
				} else {
					message = "Please fix the following errors: ";

					for (int i = 0; i < size; i++) {
						message += "\n" + (i + 1) + ". " + exceptions.get(i).getMessage();
					}

					JOptionPane.showMessageDialog(this, message, "Validation Errors", JOptionPane.WARNING_MESSAGE);
				}
			}

		} else if (source == btnReset) {
			reset();
		}

	}

	private void reset() {
		txtUnicornID.setText("");
		txtName.setText("");
		txtType.setText("");
		txtRate.setText("");
		txtColor.setText("");
		chkAvailable.setSelected(false);
		chkHeatlhCheck.setSelected(true);
		txtUnicornID.grabFocus();
	}
}