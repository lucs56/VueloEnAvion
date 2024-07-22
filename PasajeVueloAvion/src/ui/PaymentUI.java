package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import payment.PaymentManager;

public class PaymentUI {
    private JPanel panel;
    private JTextField amountField;
    private JTextField methodField;
    private JTextField statusField;
    private JButton payButton;
    private String reservationId;

    public PaymentUI(String reservationId) {
        this.reservationId = reservationId;
        panel = new JPanel();
        panel.setLayout(null);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(10, 20, 80, 25);
        panel.add(amountLabel);

        amountField = new JTextField(20);
        amountField.setBounds(100, 20, 165, 25);
        panel.add(amountField);

        JLabel methodLabel = new JLabel("Method:");
        methodLabel.setBounds(10, 50, 80, 25);
        panel.add(methodLabel);

        methodField = new JTextField(20);
        methodField.setBounds(100, 50, 165, 25);
        panel.add(methodField);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(10, 80, 80, 25);
        panel.add(statusLabel);

        statusField = new JTextField(20);
        statusField.setBounds(100, 80, 165, 25);
        panel.add(statusField);

        payButton = new JButton("Pay");
        payButton.setBounds(10, 110, 80, 25);
        panel.add(payButton);

        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                String method = methodField.getText();
                String status = statusField.getText();
                if (PaymentManager.makePayment(reservationId, amount, method, status)) {
                    JOptionPane.showMessageDialog(panel, "Payment successful.");
                } else {
                    JOptionPane.showMessageDialog(panel, "Payment failed.");
                }
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
