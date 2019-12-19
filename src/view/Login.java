package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;

public class Login extends JFrame {
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField username;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        username = new JTextField();
        username.setBounds(170, 139, 184, 28);
        contentPane.add(username);
        username.setColumns(10);

        JButton btnNewButton = new JButton("Login");
        btnNewButton.setForeground(Color.DARK_GRAY);
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNewButton.setBounds(109, 251, 89, 23);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // try {
                // Connection connection=ConnectionManager.getConnection();
                // String query="select username, password from userdata where username=? and
                // password =?";
                // PreparedStatement ps=connection.prepareStatement(query);
                // ps.setString(1, username.getText());
                // ps.setString(2, password.getText());
                // ResultSet set=ps.executeQuery();
                // if(set.next())
                // {
                // JOptionPane.showMessageDialog(null, "Login Sucessfull");
                // write your code here
                // }else
                // {
                // JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                // return;
                // }

                // } catch (Exception e) {
                // TODO: handle exception
                // }

            }
        });
        contentPane.add(btnNewButton);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setForeground(Color.DARK_GRAY);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblUsername.setBounds(77, 135, 81, 34);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setForeground(Color.DARK_GRAY);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPassword.setBounds(77, 191, 81, 23);
        contentPane.add(lblPassword);

        JButton btnExit = new JButton("Exit");
        btnExit.setForeground(Color.DARK_GRAY);
        btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnExit.setBounds(233, 250, 97, 25);
        contentPane.add(btnExit);

        JLabel lblUnicornCorp = new JLabel("Unicorn Corp");
        lblUnicornCorp.setForeground(Color.DARK_GRAY);
        lblUnicornCorp.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblUnicornCorp.setBounds(135, 13, 156, 49);
        contentPane.add(lblUnicornCorp);

        JLabel lblUnicornRentalManegment = new JLabel("Unicorn Rental Manegment System");
        lblUnicornRentalManegment.setForeground(Color.DARK_GRAY);
        lblUnicornRentalManegment.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUnicornRentalManegment.setBounds(67, 56, 312, 35);
        contentPane.add(lblUnicornRentalManegment);

        passwordField = new JPasswordField();
        passwordField.setBounds(170, 189, 184, 28);
        contentPane.add(passwordField);

        setUndecorated(true);
    }
}