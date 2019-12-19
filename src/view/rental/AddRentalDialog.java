package view.rental;

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

import controller.manager.RentalManager;
import controller.validator.InvalidNumberException;
import controller.validator.MaximumLengthException;
import controller.validator.MaximumNumberException;
import controller.validator.MinimumNumberException;
import controller.validator.RequiredFieldException;
import controller.validator.Validator;
import model.Rental;

public class AddRentalDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JTextField txtRentalID = new JTextField();
	private JTextField txtStartDate = new JTextField(15);
	private JTextField txtEndDate = new JTextField(15);
	private JTextField txtDepositPaid = new JTextField();
	private JTextField txtAdditionalCharges = new JTextField();
	private JTextField txtIncurredCharges = new JTextField();
	private JCheckBox chkReturned = new JCheckBox("Auto");
	private JButton btnSubmit = new JButton("Submit");
	private JButton btnReset = new JButton("Reset");

	public AddRentalDialog(ManageRentalDialog dialog) {
		super(dialog, "Add Rental", true);

		JPanel pnlCenter = new JPanel(new GridLayout(6, 2, 10, 10));
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		pnlSouth.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

		pnlCenter.add(new JLabel("Rental ID: ", JLabel.RIGHT));
		pnlCenter.add(txtRentalID);
		pnlCenter.add(new JLabel("StartDate: ", JLabel.RIGHT));
		pnlCenter.add(txtStartDate);
		pnlCenter.add(new JLabel("EndDate: ", JLabel.RIGHT));
		pnlCenter.add(txtEndDate);
		pnlCenter.add(new JLabel("DepositPaid (RM): ", JLabel.RIGHT));
		pnlCenter.add(txtDepositPaid);
		pnlCenter.add(new JLabel("Additional Charges (RM): ", JLabel.RIGHT));
		pnlCenter.add(txtAdditionalCharges);
		pnlCenter.add(new JLabel("Icurred Charges (RM): ", JLabel.RIGHT));
		pnlCenter.add(txtIncurredCharges);
		pnlCenter.add(new JLabel("Can returned on not?: ", JLabel.RIGHT));
		pnlCenter.add(chkReturned);


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
			int rentalID = 0;
			long startDate = 0, endDate = 0;
			double depositPaid = 0, additionalCharges = 0, incurredCharges = 0;

			try {
				rentalID = Validator.validate("Rental ID", txtRentalID.getText(), true, true, true, 4, 12);
			} catch (RequiredFieldException | InvalidNumberException | MinimumNumberException | MaximumNumberException e) {
				exceptions.add(e);
			}

			try {
				 startDate = Validator.validate("StartDate", txtStartDate.getText(), true, true, true, 5, 20);
			} catch (RequiredFieldException | InvalidNumberException | MinimumNumberException | MaximumNumberException e) {
				exceptions.add(e);
			}
			
			try {
				endDate = Validator.validate("EndDate", txtEndDate.getText(), true, true, true, 5 , 20);
			} catch (RequiredFieldException | InvalidNumberException | MinimumNumberException | MaximumNumberException e) {
				exceptions.add(e);
			}

			try {
				depositPaid = Validator.validate("DepositPaid", txtDepositPaid.getText(), true, true, true, 5, 20);
			} catch (RequiredFieldException | InvalidNumberException | MinimumNumberException
					| MaximumNumberException e) {
				exceptions.add(e);
			}
			
			try {
				additionalCharges = Validator.validate("AdditionalCharges", txtAdditionalCharges.getText(), true, true, true, 5, 20);
			} catch (RequiredFieldException | InvalidNumberException | MinimumNumberException
					| MaximumNumberException e) {
				exceptions.add(e);
			}
			
			try {
				incurredCharges = Validator.validate("IncurredCharges", txtIncurredCharges.getText(), true, true, true, 5, 20);
			} catch (RequiredFieldException | InvalidNumberException | MinimumNumberException
					| MaximumNumberException e) {
				exceptions.add(e);
			}


			int size = exceptions.size();

			if (size == 0) {

				Rental rental = new Rental();

				rental.setRentalID(rentalID);
				rental.setStartDate(startDate);
				rental.setEndDate(endDate);
				rental.setDepositPaid(depositPaid);
				rental.setAdditionalCharges(additionalCharges);
				rental.setIncurredCharges(incurredCharges);

				try {
					if (RentalManager.addRental(rental) != -1) {
						JOptionPane.showMessageDialog(this,
								"Rental with ID " + rental.getUnicornID() + " has been succesfully added.", "Success",
								JOptionPane.INFORMATION_MESSAGE);
						reset();

					} else {
						JOptionPane.showMessageDialog(this, "Unable to add new Rental.", "Unsuccesful",
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
		txtRentalID.setText("");
		txtStartDate.setText("");
		txtEndDate.setText("");
		txtDepositPaid.setText("");

		txtRentalID.grabFocus();
	}
}