package vueloenavion.ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaResultadosVuelos extends JFrame {
    public VentanaResultadosVuelos(List<Vuelo> vuelos) {
        setTitle("Resultados de Búsqueda de Vuelos");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"Aerolínea", "Origen", "Destino", "Fecha de Salida", "Hora de Salida", "Fecha de Llegada", "Hora de Llegada", "Duración", "Precio"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Vuelo vuelo : vuelos) {
            Object[] rowData = {
                    vuelo.getAerolinea(),
                    vuelo.getOrigen(),
                    vuelo.getDestino(),
                    vuelo.getFechaSalida(),
                    vuelo.getHoraSalida(),
                    vuelo.getFechaLlegada(),
                    vuelo.getHoraLlegada(),
                    vuelo.getDuracion(),
                    vuelo.getPrecio()
            };
            model.addRow(rowData);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}
