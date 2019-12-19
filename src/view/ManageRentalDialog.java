package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public class ManageRentalDialog extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JButton btnAddRental = new JButton("Add Rental");
	private JButton btnUpdateRental = new JButton("Search Rental");
	private JButton btnDeleteRental = new JButton("Delete Rental");
	private JButton btnListRental = new JButton("List Rental");

	public ManageRentalDialog(MainFrame frame) {
		super(frame, "Manage Rental", true);
		
		GridLayout layout = new GridLayout(8, 1, 10, 10);
		
		this.add(btnAddRental);
		this.add(btnUpdateRental);
		this.add(btnDeleteRental);
		this.add(btnListRental);


		btnAddRental.addActionListener(this);
		btnUpdateRental.addActionListener(this);
		btnDeleteRental.addActionListener(this);
		btnListRental.addActionListener(this);

		this.setLayout(layout);
		this.pack();
		this.setLocationRelativeTo(frame);
		this.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();

		if(source == btnAddUnicorn){
			new AddCarDialog(this);
		} else if (source == btnViewAllCars){
			new ViewCarsDialog(this);
		}

	}


}