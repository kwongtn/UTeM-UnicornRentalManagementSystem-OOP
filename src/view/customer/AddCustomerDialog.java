package view.customer;

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

import controller.manager.CustomerManager;
import controller.validator.InvalidNumberException;
import controller.validator.MaximumLengthException;
import controller.validator.MaximumNumberException;
import controller.validator.MinimumNumberException;
import controller.validator.RequiredFieldException;
import controller.validator.Validator;
import model.Customer;

public class AddCustomerDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JTextField txtName = new JTextField();
	private JTextField txtPhoneNo = new JTextField(15);
	private JTextField txtUnicornLicenseID = new JTextField();

	private JButton btnSubmit = new JButton("Submit");
	private JButton btnReset = new JButton("Reset");

	public AddCustomerDialog(ManageCustomerDialog dialog) {
		super(dialog, "Add Customer", true);

		JPanel pnlCenter = new JPanel(new GridLayout(6, 2, 10, 10));
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		pnlSouth.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

		pnlCenter.add(new JLabel("Name: ", JLabel.RIGHT));
		pnlCenter.add(txtName);
		pnlCenter.add(new JLabel("PhoneNo: ", JLabel.RIGHT));
		pnlCenter.add(txtPhoneNo);
		pnlCenter.add(new JLabel("UnicornLicenseID: ", JLabel.RIGHT));
		pnlCenter.add(txtUnicornLicenseID);


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
			String Name = null;
			String phoneNo = null;
			int unicornLicenseID = 0;

			

			try {
				Name = Validator.validate("Name", txtName.getText(), true, 15);
			} catch (RequiredFieldException | MaximumLengthException e) {
				exceptions.add(e);
			}

			try {
				phoneNo = Validator.validate("PhoneNo", txtPhoneNo.getText(), true, 50);
			} catch (RequiredFieldException | MaximumLengthException e) {
				exceptions.add(e);
			}

			try {
				unicornLicenseID = Validator.validate("UnicornLicenseID", txtUnicornLicenseID.getText(), true, true, true, 12, 4);
			} catch (RequiredFieldException | InvalidNumberException | MinimumNumberException
					| MaximumNumberException e) {
				exceptions.add(e);
			}


			int size = exceptions.size();

			if (size == 0) {

				Customer customer = new Customer();

				customer.setName(Name);
				customer.setPhoneNo(phoneNo);
				customer.setUnicornLicenseID(unicornLicenseID);
	

				try {
					if (CustomerManager.addCustomer(customer) != -1) {
						JOptionPane.showMessageDialog(this,
								"Customer with ID " + customer.getCustomerID() + " has been succesfully added.", "Success",
								JOptionPane.INFORMATION_MESSAGE);
						reset();

					} else {
						JOptionPane.showMessageDialog(this, "Unable to add new car.", "Unsuccesful",
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
		txtName.setText("");
		txtPhoneNo.setText("");
		txtUnicornLicenseID.setText("");

		txtName.grabFocus();
	}
}