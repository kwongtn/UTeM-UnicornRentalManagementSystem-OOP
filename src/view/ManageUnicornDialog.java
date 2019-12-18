package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public class ManageUnicornDialog extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JButton btnAddUnicorn = new JButton("Add Unicorn");
	private JButton btnSearchUnicorn = new JButton("Search Unicorn");
	private JButton btnDeleteUnicorn = new JButton("Delete Unicorn");
	private JButton btnListUnicorn = new JButton("List Unicorn");

	public void ManageUnicornDialog(MainFrame frame) {
		super(frame, "Manage Unicorn", true);
		
		GridLayout layout = new GridLayout(8, 1, 10, 10);
		
		this.add(btnAddUnicorn);
		this.add(btnSearchUnicorn);
		this.add(btnDeleteUnicorn);
		this.add(btnListUnicorn);


		btnAddUnicorn.addActionListener(this);
		btnSearchUnicorn.addActionListener(this);
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

		if(source == btnAddUnicorn){
			new AddUnicornDialog(this);
		}

	}
}
