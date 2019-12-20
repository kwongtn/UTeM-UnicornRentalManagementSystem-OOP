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

import controller.manager.CustomerManager;
import controller.manager.RentalManager;
import controller.manager.UnicornManager;
import controller.validator.MaximumLengthException;
import controller.validator.RequiredFieldException;
import controller.validator.Validator;
import model.Rental;

public class UpdateRentalDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JTextField txtUnicornID = new JTextField(15);
	private JTextField txtCustomerID = new JTextField();
	private JTextField txtStartDate = new JTextField();
	private JTextField txtEndDate = new JTextField();
    private JTextField txtDepositPaid = new JTextField();
    private JTextField txtIncurredCharges = new JTextField();
	private JCheckBox chkIsReturned = new JCheckBox();
	private JButton btnSubmit = new JButton("Submit");
	private JButton btnReset = new JButton("Reset");
	private Rental rental;

	public UpdateRentalDialog(ManageRentalDialog dialog, Rental rental) {
		super(dialog, "Update Rental", true);
		this.rental = rental;

		JPanel pnlCenter = new JPanel(new GridLayout(8, 2, 10, 10));
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		pnlSouth.setBorder(BorderFactory.createRaisedSoftBevelBorder());

		pnlCenter.add(new JLabel("RentalID: ", JLabel.RIGHT));
		pnlCenter.add(new JLabel(String.valueOf(rental.getRentalID()), JLabel.LEFT));

		pnlCenter.add(new JLabel("UnicornID: ", JLabel.RIGHT));
		pnlCenter.add(txtUnicornID);
		pnlCenter.add(new JLabel("CustomerID: ", JLabel.RIGHT));
		pnlCenter.add(txtCustomerID);
		pnlCenter.add(new JLabel("Start Date: ", JLabel.RIGHT));
		pnlCenter.add(txtStartDate);
		pnlCenter.add(new JLabel("End Date: ", JLabel.RIGHT));
		pnlCenter.add(txtEndDate);
		pnlCenter.add(new JLabel("Deposit Paid: ", JLabel.RIGHT));
		pnlCenter.add(txtDepositPaid);
		pnlCenter.add(new JLabel("Incurred Charges: ", JLabel.RIGHT));
        pnlCenter.add(txtIncurredCharges);
        pnlCenter.add(new JLabel("Returned? ", JLabel.RIGHT));
        pnlCenter.add(chkIsReturned);

		// Default inputs are defined at reset()
		reset();
		
		
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

			try {
				rental.setUnicorn(UnicornManager.getUnicornByID(Integer.parseInt(Validator.validate("Unicorn ID", txtUnicornID.getText(), true, 20))));
			}catch (RequiredFieldException | MaximumLengthException | SQLException | ClassNotFoundException e){
				exceptions.add(e);
			}

			try{
				rental.setCustomer(CustomerManager.getCustomerByID(Integer.parseInt(Validator.validate("Customer ID", txtCustomerID.getText(), true, 20))));
			}catch (RequiredFieldException | MaximumLengthException | SQLException | ClassNotFoundException e){
				exceptions.add(e);
			}
			
			try {
                rental.setStartDate(Long.parseLong(Validator.validate("Start Date", txtStartDate.getText(), true, 20)));
			} catch (RequiredFieldException| NumberFormatException | MaximumLengthException e) {
				exceptions.add(e);
            }
            
            try {
                rental.setEndDate(Long.parseLong(Validator.validate("End Date", txtEndDate.getText(), false, 20)));
			} catch (RequiredFieldException| NumberFormatException | MaximumLengthException e) {
				exceptions.add(e);
            }
            
            try {
                rental.setDepositPaid(Double.parseDouble(Validator.validate("Deposit Paid", txtDepositPaid.getText(), true, 20)));
			} catch (RequiredFieldException| NumberFormatException | MaximumLengthException e) {
				exceptions.add(e);
			}

			try {
                rental.setIncurredCharges(Double.parseDouble(Validator.validate("Incurred Charges", txtIncurredCharges.getText(), false, 20)));
			} catch (RequiredFieldException| NumberFormatException | MaximumLengthException e) {
				exceptions.add(e);
			}

			rental.setReturned(chkIsReturned.isSelected());
			
			int size = exceptions.size();
			if (size == 0) {
				try {
					if (RentalManager.updateRental(rental) != 0) {
						JOptionPane.showMessageDialog(this, "Rental has been updated.", "Success",
						JOptionPane.INFORMATION_MESSAGE);
						reset();
					} else
					JOptionPane.showMessageDialog(this, "Unable to update Rental.", "Unsuccessful",
					JOptionPane.WARNING_MESSAGE);
				} catch (ClassNotFoundException | SQLException e) {
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

			dispose();
		}
		
		else if (source == btnReset) {
			reset();
		}
	}
	
	private void reset() {
        txtUnicornID.setText(String.valueOf(rental.getUnicornID()));
        txtCustomerID.setText(String.valueOf(rental.getCustomerID()));
        txtStartDate.setText(String.valueOf(rental.getStartDate()));
        txtEndDate.setText(String.valueOf(rental.getEndDate()));
        txtDepositPaid.setText(String.valueOf(rental.getDepositPaid()));
        txtIncurredCharges.setText(String.valueOf(rental.currentIncurredCharges()));
        chkIsReturned.setSelected(Boolean.getBoolean(String.valueOf(rental.isReturned())));
	}
	
}