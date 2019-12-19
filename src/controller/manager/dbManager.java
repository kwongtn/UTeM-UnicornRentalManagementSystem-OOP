package controller.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbManager {
	protected static String dbAddress = "jdbc:mysql://localhost:8080/UNICORNDB";
	protected static String dbUsername = "root";
	protected static String dbPassword = "password";
	
	public static boolean login(String username, String password) throws SQLException, ClassNotFoundException {
		System.out.println("Received username: " + username);
		System.out.println("Received password: " + password);

		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);

		try{
			PreparedStatement ps = connection.prepareStatement("SELECT password FROM USER WHERE userName=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			rs.next();
	
			System.out.print("Actual password: " + rs.getString(1));
			
			if(String.valueOf(rs.getString(1)).equals(String.valueOf(password))){
				return true;
			} else {
				return false;
			}
		} catch (SQLException e){
			System.out.println("SQL Exception.");
			return false;
		}

		
	}

	// public static boolean testConnection() throws ClassNotFoundException, SQLException {
	// 	Class.forName("com.mysql.jdbc.Driver");
	// 	Connection connection = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
	// 	
	// 	if(connection){
	// 		
	// 	}
	// }
}