package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import view.unicorn.ManageUnicornDialog;
import view.customer.ManageCustomerDialog;
import view.rental.ManageRentalDialog;

public class MainFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JButton btnManageUnicorn = new JButton("Manage Unicorn");
	private JButton btnManageCustomers = new JButton("Manage Customers");
	private JButton btnManageRentals = new JButton("Manage Rentals");
	private JButton btnExit = new JButton("Logout");
	
	public MainFrame() {
		super("Unicorn Rental Management System");
		
		GridLayout layout = new GridLayout(4, 1, 10, 10);
		
		btnManageUnicorn.addActionListener(this);
		btnManageCustomers.addActionListener(this);
		btnManageRentals.addActionListener(this);
		btnExit.addActionListener(this);
		
		this.add(btnManageUnicorn);
		this.add(btnManageCustomers);
		this.add(btnManageRentals);
		this.add(btnExit);
	
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

		if(source == btnManageUnicorn) {
			System.out.println("Manage Unicorn selected.");
			new ManageUnicornDialog(this);
		}
		else if(source == btnManageCustomers) {
			System.out.println("Manage Customer selected.");
			new ManageCustomerDialog(this);
		}
		else if(source == btnManageRentals) {
			System.out.println("Manage Rental selected.");
			new ManageRentalDialog(this);
		} else if (source == btnExit){
			System.out.println("Exit selected.");
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
