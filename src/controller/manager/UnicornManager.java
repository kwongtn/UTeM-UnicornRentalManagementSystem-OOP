package controller.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Unicorn;
public class UnicornManager{

	private static Vector<Unicorn> unicorns = new Vector<>();

	private static String dbAddress = "jdbc:mysql://localhost:8080/UNICORNDB";
	private static String dbUsername = "root";
	private static String dbPassword = "password";

	public static int addUnicorn(Unicorn unicorn) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
		PreparedStatement ps = connection.prepareStatement("INSERT INTO UNICORN(name, type, rate, color, availability, healthCheck) VALUES (?,?,?,?,?,?)");
		
		ps.setString(1, unicorn.getName());
		ps.setString(2, unicorn.getType());
		ps.setDouble(3, unicorn.getRate());
		ps.setString(4, unicorn.getColor());
		ps.setBoolean(5, unicorn.isAvailable());
		ps.setBoolean(6, unicorn.isHealthCheck());

		int status = ps.executeUpdate();

		connection.close();

		return status;
	}

	public static int deleteUnicorn(int unicornID) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
		PreparedStatement ps = connection.prepareStatement("DELETE FROM UNICORN WHERE unicornID=?");

		ps.setInt(1, unicornID);

		int status = ps.executeUpdate();

		connection.close();

		int index = -1;
		for(int i = 0; i < unicorns.size(); i++){
			Unicorn temp = unicorns.get(i);

			if(temp != null && (temp.getUnicornID() == unicornID)){
				index = i;

				break;
			}
		}

		try {
			unicorns.remove(index);
			return status;
		} catch (Exception e) {
			return 1 + status;
		}

	}

	public static Vector<Unicorn> getUnicorns() throws ClassNotFoundException, SQLException{

		Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
		PreparedStatement ps = connection.prepareStatement("SELECT name, type, rate, color, available, healthCheck FROM Unicorn");
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