package vueloenavion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class VentanaPrincipal extends JFrame {
    private JComboBox<String> origenList;
    private JComboBox<String> destinoList;
    private JSpinner fechaSpinner;
    private JSpinner pasajerosSpinner;
    private ControladorReserva controlador;

    public VentanaPrincipal() {
        controlador = new ControladorReserva();

        setTitle("Sistema de Reserva de Vuelos");
        setSize(500, 300);
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

        String[] provincias = {"Buenos Aires", "Catamarca", "Chaco", "Chubut", "Córdoba", "Corrientes", "Entre Ríos", "Formosa", "Jujuy", "La Pampa", "La Rioja", "Mendoza", "Misiones", "Neuquén", "Río Negro", "Salta", "San Juan", "San Luis", "Santa Cruz", "Santa Fe", "Santiago del Estero", "Tierra del Fuego", "Tucumán"};
        origenList = new JComboBox<>(provincias);
        origenList.setBounds(170, 20, 200, 25);
        panel.add(origenList);

        JLabel destinoLabel = new JLabel("Destino:");
        destinoLabel.setBounds(10, 60, 150, 25);
        panel.add(destinoLabel);

        destinoList = new JComboBox<>(provincias);
        destinoList.setBounds(170, 60, 200, 25);
        panel.add(destinoList);

        JLabel fechaLabel = new JLabel("Fecha:");
        fechaLabel.setBounds(10, 100, 150, 25);
        panel.add(fechaLabel);

        fechaSpinner = new JSpinner(new SpinnerDateModel());
        fechaSpinner.setEditor(new JSpinner.DateEditor(fechaSpinner, "dd/MM/yyyy"));
        fechaSpinner.setBounds(170, 100, 200, 25);
        panel.add(fechaSpinner);

        JLabel pasajerosLabel = new JLabel("Cantidad de Pasajeros:");
        pasajerosLabel.setBounds(10, 140, 150, 25);
        panel.add(pasajerosLabel);

        pasajerosSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        pasajerosSpinner.setBounds(170, 140, 50, 25);
        panel.add(pasajerosSpinner);

        JButton siguienteButton = new JButton("Siguiente");
        siguienteButton.setBounds(10, 180, 100, 25);
        panel.add(siguienteButton);

        siguienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaPasajeros();
            }
        });
    }

    private void abrirVentanaPasajeros() {
        String origen = (String) origenList.getSelectedItem();
        String destino = (String) destinoList.getSelectedItem();
        Date fecha = (Date) fechaSpinner.getValue();
        int cantidadPasajeros = (Integer) pasajerosSpinner.getValue();

        VentanaPasajeros ventanaPasajeros = new VentanaPasajeros(origen, destino, fecha, cantidadPasajeros, controlador);
        ventanaPasajeros.setVisible(true);
        dispose();
    }
}
