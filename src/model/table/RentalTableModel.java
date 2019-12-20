package model.table;

import model.Rental;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class RentalTableModel extends DefaultTableModel {

    private static final long serialVersionUID = 1L;

    public RentalTableModel(Vector<Rental> rentals) {
        int size = rentals.size();
        String[][] data = new String[size][8];

        for (int i = 0; i < size; i++) {
            Rental rental = rentals.get(i);

            data[i][0] = String.valueOf(rental.getRentalID());
            data[i][1] = String.valueOf(rental.getUnicornID());
            data[i][2] = String.valueOf(rental.getCustomerID());
            data[i][3] = String.valueOf(rental.getStartDate());
            data[i][4] = String.valueOf(rental.getEndDate());
            data[i][5] = String.valueOf(rental.getDepositPaid());
            data[i][6] = String.valueOf(rental.getIncurredCharges());
            data[i][7] = String.valueOf(rental.isReturned());
            
        }

        setDataVector(data, new String[] { "RentalID", "UnicornID", "CustomerID", "Start Date", "End Date", "Deposit Paid", "Incurred Charges", "Returned?" });
    }

}