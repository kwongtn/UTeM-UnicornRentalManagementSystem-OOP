package model.table;

import model.Customer;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class CustomerTableModel extends DefaultTableModel {

    private static final long serialVersionUID = 1L;

    public CustomerTableModel(Vector<Customer> customers) {
        int size = customers.size();
        String[][] data = new String[size][4];

        for (int i = 0; i < size; i++) {
            Customer customer = customers.get(i);

            data[i][0] = String.valueOf(customer.getCustomerID());
            data[i][1] = String.valueOf(customer.getName());
            data[i][2] = String.valueOf(customer.getPhoneNo());
            data[i][3] = String.valueOf(customer.getUnicornLicenseID());
        }

        setDataVector(data, new String[] { "CustomerID", "Name", "Phone No.", "License ID" });
    }

}