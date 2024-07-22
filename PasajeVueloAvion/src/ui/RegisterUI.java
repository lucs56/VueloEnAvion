package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import user.UserManager;

public class RegisterUI {
    private JPanel panel;
    private JTextField emailField;
    private JTextField nameField;
    private JTextField birthdateField;
    private JTextField passportField;
    private JPasswordField passwordField;

    public RegisterUI() {
        panel = new JPanel();
        panel.setLayout(null);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 20, 80, 25);
        panel.add(emailLabel);

        emailField = new JTextField(20);
        emailField.setBounds(100, 20, 165, 25);
        panel.add(emailField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 50, 80, 25);
        panel.add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(100, 50, 165, 25);
        panel.add(nameField);

        JLabel birthdateLabel = new JLabel("Birthdate:");
        birthdateLabel.setBounds(10, 80, 80, 25);
        panel.add(birthdateLabel);

        birthdateField = new JTextField(20);
        birthdateField.setBounds(100, 80, 165, 25);
        panel.add(birthdateField);

        JLabel passportLabel = new JLabel("Passport:");
        passportLabel.setBounds(10, 110, 80, 25);
        panel.add(passportLabel);

        passportField = new JTextField(20);
        passportField.setBounds(100, 110, 165, 25);
        panel.add(passportField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 140, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 140, 165, 25);
        panel.add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(10, 170, 80, 25);
        panel.add(registerButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 170, 80, 25);
        panel.add(backButton);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String name = nameField.getText();
                String birthdate = birthdateField.getText();
                String passport = passportField.getText();
                String password = new String(passwordField.getPassword());

                if (UserManager.register(email, name, birthdate, passport, password)) {
                    JOptionPane.showMessageDialog(panel, "Registration successful.");
                } else {
                    JOptionPane.showMessageDialog(panel, "Registration failed.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Logic to go back to the previous screen
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
