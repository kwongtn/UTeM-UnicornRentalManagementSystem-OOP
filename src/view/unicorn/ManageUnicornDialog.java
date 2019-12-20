package view.unicorn;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import controller.manager.UnicornManager;
import model.Unicorn;
import view.MainFrame;

public class ManageUnicornDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnAddUnicorn = new JButton("Add Unicorn");
	private JButton btnUpdateUnicorn = new JButton("Search Unicorn");
	private JButton btnDeleteUnicorn = new JButton("Delete Unicorn");
	private JButton btnListUnicorn = new JButton("List Unicorn");

	public ManageUnicornDialog(MainFrame frame) {
		super(frame, "Manage Unicorn", true);

		GridLayout layout = new GridLayout(4, 1, 10, 10);

		this.add(btnAddUnicorn);
		this.add(btnUpdateUnicorn);
		this.add(btnDeleteUnicorn);
		this.add(btnListUnicorn);

		btnAddUnicorn.addActionListener(this);
		btnUpdateUnicorn.addActionListener(this);
		btnDeleteUnicorn.addActionListener(this);
		btnListUnicorn.addActionListener(this);

		this.setLayout(layout);
		this.pack();
		this.setLocationRelativeTo(frame);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();

		if (source == btnAddUnicorn) {
			new AddUnicornDialog(this);
		} else if (source == btnUpdateUnicorn) {
			Unicorn temp = null;
			try {
				temp = UnicornManager.getUnicornByID(
						Integer.parseInt(JOptionPane.showInputDialog(this, "Please enter unicornID to be edited:")));
			} catch (NumberFormatException | HeadlessException | ClassNotFoundException | SQLException | NullPointerException e) {
				JOptionPane.showMessageDialog(this, "Please enter a valid unicornID", "Error", JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
			}
			
			new UpdateUnicornDialog(this, temp);
		} else if (source == btnDeleteUnicorn){
			new DeleteUnicornDialog(this);
		} else if (source == btnListUnicorn){
			new ListUnicornDialog(this);
		}

	}
}
