package ui;

import reservation.ReservationManager; // Asegúrate de que esta clase tenga el método getFlights()
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.util.Vector;

public class FlightSearchUI {
    private JPanel panel;
    private JTable flightTable;
    private JButton reserveButton;

    public FlightSearchUI() {
        panel = new JPanel();
        flightTable = new JTable();
        reserveButton = new JButton("Reserve");

        panel.add(new JScrollPane(flightTable));
        panel.add(reserveButton);

        // Cargar vuelos al inicializar la UI
        loadFlights();

        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = flightTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String flightId = flightTable.getValueAt(selectedRow, 0).toString();
                    openReservationUI(flightId);
                } else {
                    JOptionPane.showMessageDialog(panel, "Please select a flight to reserve.");
                }
            }
        });
    }

    private void loadFlights() {
        try {
            ResultSet rs = ReservationManager.getFlights(); // Asegúrate de que este método esté implementado en ReservationManager
            flightTable.setModel(buildTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Error loading flights. Please try again later.");
        }
    }

    private void openReservationUI(String flightId) {
        JFrame frame = new JFrame("Reservation");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.add(new ReservationUI(flightId).getPanel()); // Usa getPanel() para obtener el JPanel
        frame.setVisible(true);
    }

    private static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        Vector<String> columnNames = new Vector<>();
        Vector<Vector<Object>> data = new Vector<>();

        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        while (rs.next()) {
            Vector<Object> row = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                row.add(rs.getObject(columnIndex));
            }
            data.add(row);
        }

        return new DefaultTableModel(data, columnNames);
    }

    public JPanel getPanel() {
        return panel;
    }
}
