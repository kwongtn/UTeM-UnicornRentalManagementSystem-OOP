package view.unicorn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.manager.UnicornManager;
import model.table.UnicornTableModel;

public class ListUnicornDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTable tblUnicorn = new JTable();
	
	public ListUnicornDialog(ManageUnicornDialog frame) {
		super(frame,"Unicorn Listing",true);
		
		try {
			tblUnicorn.setModel(new UnicornTableModel(UnicornManager.getUnicorns()));
		}catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // TODO TN Check
        }
		this.add(new JScrollPane(tblUnicorn));
		this.pack();
        this.setLocationRelativeTo(frame);
        this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent event) {

	}

}
