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
import controller.validator.MaximumLengthException;
import controller.validator.RequiredFieldException;
import controller.validator.Validator;
import model.Unicorn;

public class UpdateUnicornDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JTextField txtName = new JTextField(15);
	private JTextField txtRate = new JTextField();
	private JTextField txtType = new JTextField();
	private JTextField txtColor = new JTextField();
	private JCheckBox chkAvailable = new JCheckBox();
	private JCheckBox chkHeatlhCheck = new JCheckBox("Yes");
	private JButton btnSubmit = new JButton("Submit");
	private JButton btnReset = new JButton("Reset");
	private Unicorn unicorn;

	public UpdateUnicornDialog(ManageUnicornDialog dialog, Unicorn unicorn) {
		super(dialog, "Update Unicorn", true);
		this.unicorn = unicorn;

		JPanel pnlCenter = new JPanel(new GridLayout(7, 2, 10, 10));
		JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		pnlSouth.setBorder(BorderFactory.createRaisedSoftBevelBorder());

		pnlCenter.add(new JLabel("UnicornID: ", JLabel.RIGHT));
		pnlCenter.add(new JLabel(String.valueOf(unicorn.getUnicornID()), JLabel.LEFT));

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
		pnlCenter.add(new JLabel("Health Checked?: ", JLabel.RIGHT));
		pnlCenter.add(chkHeatlhCheck);

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
				unicorn.setName(Validator.validate("Name", txtName.getText(), true, 20));				
			}catch (RequiredFieldException | MaximumLengthException e){
				exceptions.add(e);
			}

			try{
				unicorn.setType(Validator.validate("Type", txtType.getText(), true, 20));
			}catch (RequiredFieldException | MaximumLengthException e){
				exceptions.add(e);
			}
			
			try {
				unicorn.setRate(Double.parseDouble(Validator.validate("Rate", txtRate.getText(), true, 20)));
			} catch (RequiredFieldException| NumberFormatException | MaximumLengthException e) {
				exceptions.add(e);
			}

			try {
				unicorn.setColor(Validator.validate("Color", txtColor.getText(), true, 20));
			} catch (RequiredFieldException| NumberFormatException | MaximumLengthException e) {
				exceptions.add(e);
			}

			unicorn.setAvailable(chkAvailable.isSelected());
			unicorn.setHealthCheck(chkHeatlhCheck.isSelected());
			
			int size = exceptions.size();
			if (size == 0) {
				try {
					if (UnicornManager.updateUnicorn(unicorn) != 0) {
						JOptionPane.showMessageDialog(this, "Unicorn has been updated.", "Success",
						JOptionPane.INFORMATION_MESSAGE);
						reset();
					} else
					JOptionPane.showMessageDialog(this, "Unable to update Unicorn.", "Unsuccessful",
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
		txtName.setText(String.valueOf(unicorn.getName()));
		txtType.setText(String.valueOf(unicorn.getType()));
		txtRate.setText(String.valueOf(unicorn.getRate()));
		txtColor.setText(String.valueOf(unicorn.getColor()));
		chkAvailable.setSelected(Boolean.getBoolean(String.valueOf(unicorn.isAvailable())));
		chkAvailable.setSelected(Boolean.getBoolean(String.valueOf(unicorn.isHealthCheck())));
	}
	
}