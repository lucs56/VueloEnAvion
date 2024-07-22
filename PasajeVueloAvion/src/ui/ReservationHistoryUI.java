package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import reservation.ReservationManager;

public class ReservationHistoryUI {
    private JFrame frame;
    private JTextArea historyArea;
    private String userEmail;

    public ReservationHistoryUI(String email) {
        this.userEmail = email;
        frame = new JFrame("Reservation History");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel(new BorderLayout());

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);

        panel.add(scrollPane, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String history = ReservationManager.getReservationHistory(userEmail);
                    historyArea.setText(history);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error retrieving reservation history.");
                }
            }
        });

        panel.add(refreshButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        // Load reservation history when the UI is shown
        refreshButton.doClick(); // Automatically load the history on startup
    }

    public JFrame getFrame() {
        return frame;
    }
}
