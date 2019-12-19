package view.rental;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.manager.RentalManager;
import model.table.RentalTableModel;

public class ListRentalDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTable tblRental = new JTable();
	
	public ListRentalDialog(ManageRentalDialog frame) {
		super(frame,"Rental Listing",true);
		
		try {
			tblRental.setModel(new RentalTableModel(RentalManager.getRentals()));
		}catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // TODO TN Check
        }
		this.add(new JScrollPane(tblRental));
		this.pack();
        this.setLocationRelativeTo(frame);
        this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent event) {


	}

}
