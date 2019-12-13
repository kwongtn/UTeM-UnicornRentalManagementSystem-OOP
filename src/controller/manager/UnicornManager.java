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

	public static int addUnicorn(Unicorn unicorn) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:8080/UNICORNDB", "root", "password");
		PreparedStatement ps = connection.prepareStatement("INSERT INTO UNICORN(name, type, rate, color, availability, healthCheck) VALUES (?,?,?,?,?,?)");
		
		ps.setString(1, unicorn.getPlateNo());
		ps.setString(2, unicorn.getModel());
		ps.setDouble(3, unicorn.getPrice());
		ps.setInt(4, unicorn.getCapacity());
		ps.setBoolean(5, unicorn.isAuto());
		ps.setBoolean(6, unicorn.isUsable());

		int status = ps.executeUpdate();

		connection.close();

		return status;
	}

	public static int updateUnicorn(Unicorn unicorn){
		int index = -1;

		for (int i = 0; i < unicorns.size(); i++){
			Unicorn temp = unicorns.get(i);

			if(temp != null && (temp.getUnicornID() == unicorn.getUnicornID())){
				unicorns.set(index, unicorn);
				index = i;

				break;
			}
		}

		return index;
	}

	public static int deleteUnicorn(int unicornID){
		int index = -1;

		for (int i = 0; i < unicorns.size(); i++){
			Unicorn temp = unicorns.get(i);

			if(temp != null && (temp.getUnicornID() == unicornID)){
				// unicorns[i] = null;
				index = i;

				break;
			}
		}

		return unicorns.remove(index) != null ? 1 : 0;
	}

	public static Vector<Unicorn> getunicorns() throws ClassNotFoundException, SQLException{

		Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbCRMS_B031810219", "root", "password");
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM Unicorn");
		ResultSet rs = ps.executeQuery();

		Vector<Unicorn> unicorns = new Vector<>();

		while(rs.next()){
			Unicorn unicorn = new Unicorn();

			unicorn.setPlateNo(rs.getString(2));
			unicorn.setModel(rs.getString(3));
			unicorn.setPrice(rs.getDouble(4));
			unicorn.setCapacity(rs.getInt(5));
			unicorn.setAuto(rs.getBoolean(6));
			unicorn.setUsable(rs.getBoolean(7));

			unicorns.add(unicorn);
		}

		connection.close();

		return unicorns;
	}
	
	public static Vector<Unicorn> getunicorns(double maxPrice){
		Vector<Unicorn> temp = new Vector<>();
		for(Unicorn unicorn : unicorns) {
			if(unicorn.getPrice() < maxPrice) {
				temp.add(unicorn);
			}
		}
		
		return temp;
	}

	public static Vector<Unicorn> getunicorns(int minCapacity){
		Vector<Unicorn> temp = new Vector<>();
		for(Unicorn unicorn : unicorns) {
			if(unicorn.getCapacity() < minCapacity) {
				temp.add(unicorn);
			}
		}
		
		return temp;
	}
	
	public static Vector<Unicorn> getunicorns(boolean auto){
		Vector<Unicorn> temp = new Vector<>();
		for(Unicorn unicorn : unicorns) {
			if(unicorn.isAuto() == auto) {
				temp.add(unicorn);
			}
		}
		
		return temp;
	}
	
	public static Vector<Unicorn> getunicorns(String model){
		Vector<Unicorn> temp = new Vector<>();
		for(Unicorn unicorn : unicorns) {
			if(unicorn != null && unicorn.getModel().toLowerCase().contains(model.toLowerCase())) {
				temp.add(unicorn);
			}
		}
		
		return temp;
	}
	
	public static Unicorn getUnicorn(int unicornID){
		Unicorn temp = null;
		
		for(Unicorn unicorn : unicorns) {
			if (unicorn.getUnicornID() == unicornID) {
				temp = unicorn;
				break;
			}
		}
		
		return temp;
	}
	
}