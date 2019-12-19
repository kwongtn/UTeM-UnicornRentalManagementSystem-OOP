package controller.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Customer;

public class CustomerManager extends dbManager{

	private static Vector<Customer> customers = new Vector<>();

	public static int addCustomer(Customer customer) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
		PreparedStatement ps = connection.prepareStatement("INSERT INTO CUSTOMER(name, phoneNo, unicornLicenseID) VALUES (?,?,?)");
		
		ps.setString(1, customer.getName());
		ps.setString(2, customer.getPhoneNo());
		ps.setInt(3,customer.getUnicornLicenseID());

		int status = ps.executeUpdate();

		customer.setCustomerID(connection.prepareStatement("SELECT MAX(customerID) FROM CUSTOMER").executeQuery().getInt(1));

		connection.close();

		return status;
	}

	public static int deleteCustomer(int customerID) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
		PreparedStatement ps = connection.prepareStatement("DELETE FROM CUSTOMER WHERE customerID=?");

		ps.setInt(1, customerID);

		int status = ps.executeUpdate();

		connection.close();

		int index = -1;
		for(int i = 0; i < customers.size(); i++){
			Customer temp = customers.get(i);

			if(temp != null && (temp.getCustomerID() == customerID)){
				index = i;

				break;
			}
		}

		try {
			customers.remove(index);
			return status;
		} catch (Exception e) {
			return 1 + status;
		}

	}

	public static int updateCustomer(Customer customer) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
		PreparedStatement ps = connection.prepareStatement("UPDATE CUSTOMER SET name=?, phoneNo=?, unicornLicenseID=? WHERE customerID=?");

		ps.setString(1, customer.getName());
		ps.setString(2, customer.getPhoneNo());
		ps.setInt(3, customer.getUnicornLicenseID());
		ps.setInt(4, customer.getCustomerID());

		int status = ps.executeUpdate();

		
		int index = -1;
		for (int i = 0; i < customers.size(); i++) {
			Customer temp = customers.get(i);
			if (temp != null && (temp.getCustomerID() == customer.getCustomerID())) {
				customers.set(index, customer);
				index = i;
				break;
			}
		}

		return status;
	}

	public static Vector<Customer> getCustomers() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM CUSTOMER");
		ResultSet rs = ps.executeQuery();

		Vector<Customer> customers = new Vector<>();

		while (rs.next()) {
			Customer customer = new Customer();

			customer.setCustomerID(rs.getInt(1));
			customer.setName(rs.getString(2));
			customer.setPhoneNo(rs.getString(3));
			customer.setUnicornLicenseID(rs.getInt(4));

			customers.add(customer);
		}

		connection.close();

		return customers;
	}

}