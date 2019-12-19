package controller.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Rental;
import controller.manager.UnicornManager;

public class RentalManager extends dbManager{

	private static Vector<Rental> rentals = new Vector<>();

	public static int addRental(Rental rental) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
		PreparedStatement ps = connection.prepareStatement("INSERT INTO RENTAL(unicornID, customerID, start, end, depositPaid) VALUES (?,?,?,?,?)");
		
        ps.setInt(1, rental.getUnicornID());
        ps.setInt(2, rental.getCustomerID());
        ps.setLong(3, rental.getStartDate());
        ps.setLong(4, rental.getEndDate());
        ps.setDouble(5, rental.getDepositPaid());

		int status = ps.executeUpdate();

		rental.setRentalID(connection.prepareStatement("SELECT MAX(rentalID) FROM RENTAL").executeQuery().getInt(1));

		connection.close();

		return status;
	}

	public static int deleteRental(int rentalID) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
		PreparedStatement ps = connection.prepareStatement("DELETE FROM RENTAL WHERE rentalID=?");

		ps.setInt(1, rentalID);

		int status = ps.executeUpdate();

		connection.close();

		int index = -1;
		for(int i = 0; i < rentals.size(); i++){
			Rental temp = rentals.get(i);

			if(temp != null && (temp.getRentalID() == rentalID)){
				index = i;

				break;
			}
		}

		try {
			rentals.remove(index);
			return status;
		} catch (Exception e) {
			return 1 + status;
		}

	}

	public static int updateRental(Rental rental) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
		PreparedStatement ps = connection.prepareStatement("UPDATE RENTAL SET rentalID=?, unicornID=?, customerID=?, start=?, end=?, depositPaid=? WHERE rentalID=?");

        ps.setInt(1, rental.getRentalID());
        ps.setInt(2, rental.getUnicornID());
        ps.setInt(3, rental.getCustomerID());
        ps.setLong(4, rental.getStartDate());
        ps.setLong(5, rental.getEndDate());
        ps.setDouble(6, rental.getDepositPaid());
        ps.setInt(7, rental.getRentalID());

		int status = ps.executeUpdate();

		
		int index = -1;
		for (int i = 0; i < rentals.size(); i++) {
			Rental temp = rentals.get(i);
			if (temp != null && (temp.getRentalID() == rental.getRentalID())) {
				rentals.set(index, rental);
				index = i;
				break;
			}
		}

		return status;
	}

	public static Vector<Rental> getRentals() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM RENTAL");
		ResultSet rs = ps.executeQuery();

		Vector<Rental> rentals = new Vector<>();

		while (rs.next()) {
			Rental rental = new Rental();

            rental.setRentalID(rs.getInt(1));
			rental.setUnicorn(UnicornManager.getUnicornByID(rs.getInt(2)));
			rental.setCustomer(CustomerManager.getCustomerByID(rs.getInt(3)));
			rental.setStartDate(rs.getLong(4));
			rental.setEndDate(rs.getLong(5));
			rental.setDepositPaid(rs.getDouble(6));
			rental.setIncurredCharges(rs.getDouble(7));
			rental.setAdditionalCharges(rental.getIncurredCharges() - rental.getDepositPaid());
			rental.setReturned(rs.getBoolean(8));

			rentals.add(rental);
		}

		connection.close();

		return rentals;
	}

	public static Rental getRentalByID(int rentalID) throws ClassNotFoundException, SQLException {
		Vector<Rental> rentals = getRentals();

		for(int i = 0; i < rentals.size(); i++){
			Rental rental = rentals.get(i);
			if(rental.getRentalID() == rentalID){
				return rental;
			}
		}
		
		return null;
	}

	public static Rental getCurrentRentalByUnicornID(int unicornID) throws ClassNotFoundException, SQLException {
		Vector<Rental> rentals = getRentals();

		for(int i = 0; i < rentals.size(); i++){
			Rental rental = rentals.get(i);
			if((rental.getUnicornID() == unicornID) && !rental.isReturned()){
				return rental;
			}
		}
		
		return null;
	}


}