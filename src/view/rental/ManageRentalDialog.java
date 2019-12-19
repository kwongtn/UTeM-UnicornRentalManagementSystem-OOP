package view.rental;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import view.MainFrame;

public class ManageRentalDialog extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JButton btnAddRental = new JButton("Add Rental");
	private JButton btnSearchRental = new JButton("Search Rental");
	private JButton btnDeleteRental = new JButton("Delete Rental");
	private JButton btnListRental = new JButton("List Rental");

	public ManageRentalDialog(MainFrame frame) {
		super(frame, "Manage Rental", true);
		
		GridLayout layout = new GridLayout(4, 1, 10, 10);
		
		this.add(btnAddRental);
		this.add(btnSearchRental);
		this.add(btnDeleteRental);
		this.add(btnListRental);

		btnAddRental.addActionListener(this);
		btnSearchRental.addActionListener(this);
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

		if(source == btnAddRental){
			new AddRentalDialog(this);
		} else if (source == btnSearchRental){
			// TODO: Add search rental dialog
			// new SearchRentalDialog(this);
		} else if (source == btnDeleteRental){
			new DeleteRentalDialog(this);
		} else if (source == btnListRental){
			// TODO: List rental
		}

	}


}