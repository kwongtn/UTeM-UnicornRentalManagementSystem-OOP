package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JButton btnManageCars = new JButton("Manage cars");
	private JButton btnManageCustomers = new JButton("Manage Customers");
	private JButton btnManageRentals = new JButton("Manage Rentals");
	
	public MainFrame() {
		super("Car Rental Management System");
		
		GridLayout layout = new GridLayout(3, 1, 10, 10);
		
		btnManageCars.addActionListener(this);
		btnManageCustomers.addActionListener(this);
		btnManageRentals.addActionListener(this);
		
		this.add(btnManageCars);
		this.add(btnManageCustomers);
		this.add(btnManageRentals);
	
		this.setSize(300, 200);
		this.setResizable(false);
		this.setLayout(layout);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		
		if(source == btnManageCars) {
			// new ManageCarsDialog(this);
		}
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
