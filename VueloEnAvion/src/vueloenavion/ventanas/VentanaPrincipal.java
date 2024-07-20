package vueloenavion.ventanas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import vueloenavion.controladores.ControladorVuelo;

public class VentanaPrincipal extends JFrame {
    private JComboBox<String> origenList;
    private JComboBox<String> destinoList;
    private JSpinner fechaSalidaSpinner;
    private JSpinner fechaLlegadaSpinner;
    private JSpinner pasajerosSpinner;
    private ControladorVuelo controlador;

    public VentanaPrincipal() {
        controlador = new ControladorVuelo();

        setTitle("Sistema de Reserva de Vuelos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel origenLabel = new JLabel("Origen:");
        origenLabel.setBounds(10, 20, 150, 25);
        panel.add(origenLabel);

        String[] provincias = {"Buenos Aires", "Catamarca", "Chaco", "Chubut", "Córdoba", "Corrientes", "Entre Ríos",
                "Formosa", "Jujuy", "La Pampa", "La Rioja", "Mendoza", "Misiones", "Neuquén",
                "Río Negro", "Salta", "San Juan", "San Luis", "Santa Cruz", "Santa Fe",
                "Santiago del Estero", "Tierra del Fuego", "Tucumán"};
        origenList = new JComboBox<>(provincias);
        origenList.setBounds(170, 20, 200, 25);
        panel.add(origenList);

        JLabel destinoLabel = new JLabel("Destino:");
        destinoLabel.setBounds(10, 60, 150, 25);
        panel.add(destinoLabel);

        destinoList = new JComboBox<>(provincias);
        destinoList.setBounds(170, 60, 200, 25);
        panel.add(destinoList);

        JLabel fechaSalidaLabel = new JLabel("Fecha de Salida:");
        fechaSalidaLabel.setBounds(10, 100, 150, 25);
        panel.add(fechaSalidaLabel);

        fechaSalidaSpinner = new JSpinner(new SpinnerDateModel());
        fechaSalidaSpinner.setEditor(new JSpinner.DateEditor(fechaSalidaSpinner, "dd/MM/yyyy"));
        fechaSalidaSpinner.setBounds(170, 100, 200, 25);
        panel.add(fechaSalidaSpinner);

        JLabel fechaLlegadaLabel = new JLabel("Fecha de Llegada:");
        fechaLlegadaLabel.setBounds(10, 140, 150, 25);
        panel.add(fechaLlegadaLabel);

        fechaLlegadaSpinner = new JSpinner(new SpinnerDateModel());
        fechaLlegadaSpinner.setEditor(new JSpinner.DateEditor(fechaLlegadaSpinner, "dd/MM/yyyy"));
        fechaLlegadaSpinner.setBounds(170, 140, 200, 25);
        panel.add(fechaLlegadaSpinner);

        JLabel pasajerosLabel = new JLabel("Cantidad de Pasajeros:");
        pasajerosLabel.setBounds(10, 180, 150, 25);
        panel.add(pasajerosLabel);

        pasajerosSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        pasajerosSpinner.setBounds(170, 180, 50, 25);
        panel.add(pasajerosSpinner);

        JButton buscarButton = new JButton("Buscar Vuelos");
        buscarButton.setBounds(10, 220, 150, 25);
        panel.add(buscarButton);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarVuelos();
            }
        });
    }

    private void buscarVuelos() {
        String origen = (String) origenList.getSelectedItem();
        String destino = (String) destinoList.getSelectedItem();
        Date fechaSalida = (Date) fechaSalidaSpinner.getValue();
        Date fechaLlegada = (Date) fechaLlegadaSpinner.getValue();
        int pasajeros = (Integer) pasajerosSpinner.getValue();

        List<Vuelo> vuelos = controlador.buscarVuelos(origen, destino, new java.sql.Date(fechaSalida.getTime()), new java.sql.Date(fechaLlegada.getTime()), pasajeros);

        if (!vuelos.isEmpty()) {
            VentanaResultadosVuelos ventanaResultados = new VentanaResultadosVuelos(vuelos);
            ventanaResultados.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontraron vuelos.");
        }
    }
}
