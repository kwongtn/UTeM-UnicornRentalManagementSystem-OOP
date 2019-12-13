package model;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class UnicornTableModel extends DefaultTableModel {

    private static final long serialVersionUID = 1L;

    public UnicornTableModel(Vector<Unicorn> unicorns) {
        int size = unicorns.size();
        String[][] data = new String[size][6];

        for (int i = 0; i < size; i++) {
            Unicorn unicorn = unicorns.get(i);

            data[i][0] = unicorn.getPlateNo();
            data[i][1] = unicorn.getModel();
            data[i][2] = String.valueOf(unicorn.getPrice());
            data[i][3] = String.valueOf(unicorn.getCapacity());
            data[i][4] = String.valueOf(unicorn.isAuto());
            data[i][5] = String.valueOf(unicorn.isUsable());
        }

        setDataVector(data, new String[] { "Plate No.", "Model", "Price (RM)", "Capacity", "Auto", "Usable" });
    }

}