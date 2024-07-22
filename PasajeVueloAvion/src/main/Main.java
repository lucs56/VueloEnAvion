package main;

import javax.swing.*;
import java.awt.*;
import ui.LoginUI;

public class Main {
    public static void main(String[] args) {
        // Set up the main frame
        JFrame frame = new JFrame("Flight Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Set up the LoginUI
        LoginUI loginUI = new LoginUI();
        frame.add(loginUI.getPanel(), BorderLayout.CENTER);

        // Show the frame
        frame.setVisible(true);
    }
}
