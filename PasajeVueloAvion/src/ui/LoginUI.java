package ui;

import user.UserManager; // Asegúrate de que esta clase exista y esté bien definida
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginUI {
    private JPanel panel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginUI() {
        panel = new JPanel();
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                if (UserManager.login(email, password)) {
                    JOptionPane.showMessageDialog(panel, "Login successful.");
                    openFlightSearchUI();
                } else {
                    JOptionPane.showMessageDialog(panel, "Login failed. Please check your email and password.");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRegisterUI();
            }
        });
    }

    private void openFlightSearchUI() {
        JFrame frame = new JFrame("Flight Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new FlightSearchUI().getPanel()); // Asume que FlightSearchUI tiene un método getPanel()
        frame.setVisible(true);
        ((JFrame) SwingUtilities.getWindowAncestor(panel)).dispose(); // Cierra la ventana de login
    }

    private void openRegisterUI() {
        JFrame frame = new JFrame("Register");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.add(new RegisterUI().getPanel()); // Asegúrate de que RegisterUI tenga un método getPanel()
        frame.setVisible(true);
    }

    public JPanel getPanel() {
        return panel;
    }
}
