package controller.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Customer;

public class CustomerManager{

    private static Vector<Customer> customers = new Vector<>();

    private static String dbAddress = "jdbc:mysql://localhost:8080/UNICORNDB";
	private static String dbUsername = "root";
	private static String dbPassword = "password";

    public static int addCustomer(Customer customer) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
		PreparedStatement ps = connection.prepareStatement("INSERT INTO CUSTOMER(name, phoneNo, unicornLicenseID) VALUES (?,?,?)");
        
        ps.setString(1, customer.getName());
        ps.setString(2, customer.getPhoneNo());
        ps.setInt(3, customer.getUnicornLicenseID());
        
        int status = ps.executeUpdate();

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

		connection.close();

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

		Vector<Unicorn> unicorns = new Vector<>();

		while(rs.next()){
			Unicorn unicorn = new Unicorn();

			unicorn.setName(rs.getString(1));
			unicorn.setType(rs.getString(2));
			unicorn.setRate(rs.getDouble(3));
			unicorn.setColor(rs.getString(4));
			unicorn.setAvailable(rs.getBoolean(5));
			unicorn.setHealthCheck(rs.getBoolean(6));

			unicorns.add(unicorn);
		}

		connection.close();

		return unicorns;
	}
}