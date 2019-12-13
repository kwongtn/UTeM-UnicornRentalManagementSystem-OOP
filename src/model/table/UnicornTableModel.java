package model.table;

import model.Unicorn;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class UnicornTableModel extends DefaultTableModel {

    private static final long serialVersionUID = 1L;

    public UnicornTableModel(Vector<Unicorn> unicorns) {
        int size = unicorns.size();
        String[][] data = new String[size][7];

        for (int i = 0; i < size; i++) {
            Unicorn unicorn = unicorns.get(i);

            data[i][0] = String.valueOf(unicorn.getUnicornID());
            data[i][1] = String.valueOf(unicorn.getName());
            data[i][2] = String.valueOf(unicorn.getType());
            data[i][3] = String.valueOf(unicorn.getRate());
            data[i][4] = String.valueOf(unicorn.getColor());
            data[i][5] = String.valueOf(unicorn.isAvailable());
            data[i][6] = String.valueOf(unicorn.isHealthCheck());
        }

        setDataVector(data, new String[] { "UnicornID", "Name", "Type", "Rate", "Color", "Available", "HealthCheck" });
    }

}