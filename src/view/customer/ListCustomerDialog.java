package view.customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.manager.CustomerManager;
import model.table.CustomerTableModel;

public class ListCustomerDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTable tblCustomers = new JTable();
	private JTextField search = new JTextField();
	private JButton btnSearch = new JButton("Search");
	private JPanel contentPane = new JPanel(new GridLayout(1, 2, 10, 10));
	private JPanel contentPane1 = new JPanel();
	
	public ListCustomerDialog(ManageCustomerDialog frame) {
		super(frame,"Customer Listing",true);
		
		try {
			tblCustomers.setModel(new CustomerTableModel(CustomerManager.getCustomers()));
		}catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
		contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		search.setBounds(170, 139, 184, 28);
		search.setColumns(10);
		contentPane.add(search);
		contentPane1.add(new JScrollPane(tblCustomers));
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBounds(233, 250, 97, 25);
		btnSearch.addActionListener(this);
        contentPane.add(btnSearch);
		this.add(contentPane);
		this.add(contentPane1,BorderLayout.SOUTH);
		this.pack();
        this.setLocationRelativeTo(frame);
        this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent event) {

	}

}
