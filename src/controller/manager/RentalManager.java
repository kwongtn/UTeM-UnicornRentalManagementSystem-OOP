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

}