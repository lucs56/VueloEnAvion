package ui;

import reservation.ReservationManager; // Asegúrate de que esta clase tenga el método necesario para hacer reservas
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ReservationUI {
    private JPanel panel;
    private JTextField nameField;
    private JTextField birthdateField;
    private JTextField passportField;
    private JButton reserveButton;
    private String flightId;

    public ReservationUI(String flightId) {
        this.flightId = flightId;
        panel = new JPanel();
        nameField = new JTextField(20);
        birthdateField = new JTextField(20);
        passportField = new JTextField(20);
        reserveButton = new JButton("Reserve");

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Birthdate (YYYY-MM-DD):"));
        panel.add(birthdateField);
        panel.add(new JLabel("Passport:"));
        panel.add(passportField);
        panel.add(reserveButton);

        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String birthdate = birthdateField.getText();
                String passport = passportField.getText();

                // Suponiendo que tienes el ID del usuario de alguna manera
                int userId = 1; // Cambia esto por la lógica para obtener el userId del usuario actualmente autenticado
                if (ReservationManager.makeReservation(userId, flightId, name, birthdate, passport)) {
                    JOptionPane.showMessageDialog(panel, "Reservation successful.");
                } else {
                    JOptionPane.showMessageDialog(panel, "Reservation failed. Please try again.");
                }
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}

