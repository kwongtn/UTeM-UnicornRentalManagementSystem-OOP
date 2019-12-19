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

public class UpdateUnicornDialog extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private JTextField txtName = new JTextField(15);
	private JTextField txtRate = new JTextField();
	private JTextField txtColor = new JTextField();
	private JButton btnSubmit=new JButton("Submit");
	private JButton btnReset=new JButton("Reset");
	
	
	public UpdateUnicornDialog (ManageUnicornDialog dialog)
	{
		super(dialog, "Update Unicorn",true);
		
		JPanel pnlCenter=new JPanel(new GridLayout(3,2,10,10));
		JPanel pnlSouth=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		pnlCenter.setBorder(BorderFactory.createEmptyBorder(10,10,5,10));
		pnlSouth.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		
		pnlCenter.add(new JLabel("Name: ", JLabel.RIGHT));
		pnlCenter.add(txtName);
		
		pnlCenter.add(new JLabel("Rate (RM): ", JLabel.RIGHT));
		pnlCenter.add(txtRate);
		pnlCenter.add(new JLabel("Color: ", JLabel.RIGHT));
		pnlCenter.add(txtColor);
		
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
	public void actionPerformed(ActionEvent event) 
	{
		Object source = event.getSource();
		if (source==btnSubmit)
		{
			Vector<Exception> exceptions= new Vector<>();
			String i=txtName.getText();
			String j=txtColor.getText();
			String rent = null;
			String x = null;
			int id;
			
			try 
			{
				i=Validator.validate("Unicorn ID", txtName.getText(), true, 15);
			} 
			catch (RequiredFieldException | MaximumLengthException e) 
			{
				exceptions.add(e);
			}
			
			try {
				try {
					rent = txtRate.getText();
					x = Double.valueOf(rent.trim()).doubleValue();
				}
				catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(this, "Please enter a valid number in area 'Rent' .","Unsuccessful",JOptionPane.WARNING_MESSAGE);
				}
				x = Double.valueOf(rent.trim()).doubleValue();
				x=Validator.validate("Rate", txtRate.getText(), true,20);
			} 
			catch (RequiredFieldException e) {
				exceptions.add(e);
			}
			
			int size=exceptions.size();	
			if(size==0)
			{
				try 
				{
					id = Integer.parseInt(i);
					if(UnicornManager.updateUnicorn(x, dress, id)!=0)
					{
						JOptionPane.showMessageDialog(this, "Unicorn has been updated." , "Success", JOptionPane.INFORMATION_MESSAGE);
						reset();
					}
					else
						JOptionPane.showMessageDialog(this, "Unable to update Unicorn.","Unsuccessful",JOptionPane.WARNING_MESSAGE);
				}
				catch (ClassNotFoundException | SQLException e)
				{
						JOptionPane.showMessageDialog(this, e.getMessage(), "Database Error", JOptionPane.WARNING_MESSAGE);
				}
			}
			else
			{
				String message=null;
				if(size==1)
					message=exceptions.firstElement().getMessage();
				else
				{
					message="PLease fix the following errors: ";
						
					for(int z=0;z<size;z++)
						message+="\n"+(z+1)+"."+exceptions.get(z).getMessage();
				}
					JOptionPane.showMessageDialog(this, message, "Validation Error", JOptionPane.WARNING_MESSAGE);
			}
			reset();
		}	
		
		else if(source==btnReset)
		{
			reset();
		}
	}
	
	private void reset()
	{
		txtName.setText("");
		txtColor.setText("");
		txtRate.setText("");
	}
	
}