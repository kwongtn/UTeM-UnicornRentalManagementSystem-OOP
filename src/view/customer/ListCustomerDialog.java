package view.customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.manager.CustomerManager;
import model.table.CustomerTableModel;

public class ListCustomerDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTable tblCustomers = new JTable();
	
	public ListCustomerDialog(ManageCustomerDialog frame) {
		super(frame,"Customer Listing",true);
		
		try {
			tblCustomers.setModel(new CustomerTableModel(CustomerManager.getCustomers()));
		}catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // TODO TN Check
        }
		this.add(new JScrollPane(tblCustomers));
		this.pack();
        this.setLocationRelativeTo(frame);
        this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent event) {

	}

}
