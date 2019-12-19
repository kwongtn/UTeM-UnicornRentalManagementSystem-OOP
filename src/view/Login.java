package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.manager.dbManager;
import controller.validator.MaximumLengthException;
import controller.validator.RequiredFieldException;
import controller.validator.Validator;

public class Login extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField username;
    private JTextField passwordField;
    private JButton btnLogin = new JButton("Login");
    private JLabel lblUsername = new JLabel("Username:");
    private JLabel lblPassword = new JLabel("Password:");
    private JButton btnExit = new JButton("Exit");
    private JLabel lblUnicornCorp = new JLabel("Unicorn Corp");
    private JLabel lblUnicornRentalManegment = new JLabel("Unicorn Rental Manegment System");

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

        btnLogin.setForeground(Color.DARK_GRAY);
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnLogin.setBounds(109, 251, 89, 23);
        btnLogin.addActionListener(this);
        contentPane.add(btnLogin);

        lblUsername.setForeground(Color.DARK_GRAY);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblUsername.setBounds(77, 135, 81, 34);
        contentPane.add(lblUsername);

        lblPassword.setForeground(Color.DARK_GRAY);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPassword.setBounds(77, 191, 81, 23);
        contentPane.add(lblPassword);

        // Define exit button
        btnExit.setForeground(Color.DARK_GRAY);
        btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnExit.setBounds(233, 250, 97, 25);
        btnExit.addActionListener(this);
        contentPane.add(btnExit);

        lblUnicornCorp.setForeground(Color.DARK_GRAY);
        lblUnicornCorp.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblUnicornCorp.setBounds(135, 13, 156, 49);
        contentPane.add(lblUnicornCorp);

        lblUnicornRentalManegment.setForeground(Color.DARK_GRAY);
        lblUnicornRentalManegment.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUnicornRentalManegment.setBounds(67, 56, 312, 35);
        contentPane.add(lblUnicornRentalManegment);

        passwordField = new JPasswordField();
        passwordField.setBounds(170, 189, 184, 28);
        contentPane.add(passwordField);

        setUndecorated(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source == btnExit) {
            System.exit(0);
        } else if (source == btnLogin) {
            Vector<Exception> exceptions = new Vector<>();

            String userName = null, password = null;

            try {
                userName = Validator.validate("Username", username.getText(), true, 50);
            } catch (RequiredFieldException | MaximumLengthException e) {
                exceptions.add(e);
            }

            try {
                password = Validator.validate("Password", passwordField.getText(), true, 50);
            } catch (RequiredFieldException | MaximumLengthException e) {
                exceptions.add(e);
            }

            System.out.println("Detected button login.");

            int size = exceptions.size();

            if (size == 0) {
                try {
                    boolean temp = dbManager.login(username.getText(), passwordField.getText());
                    System.out.println(temp);
                    if (temp) {
                        System.out.println("Username and password correct.");
                        dispose();
                        new MainFrame();
                    } else {
                        System.out.println("Username or Password incorrect.");
                        JOptionPane.showMessageDialog(this, "Username or password incorrect.", "Error",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    JOptionPane.showMessageDialog(this, "Something is wrong here, but we don't know why...yet.",
                            "Uh Oh!", JOptionPane.WARNING_MESSAGE);
                    e.printStackTrace();
                }
            } else {
                String message = null;
                if (size == 1) {
                    message = exceptions.firstElement().getMessage();
                } else {
                    message = "Please fix the following errors: ";

                    for (int i = 0; i < size; i++) {
                        message += "\n" + (i + 1) + ". " + exceptions.get(i).getMessage();
                    }

                    JOptionPane.showMessageDialog(this, message, "Validation Errors", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}