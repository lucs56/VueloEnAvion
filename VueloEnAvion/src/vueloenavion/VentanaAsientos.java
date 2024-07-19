package vueloenavion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VentanaAsientos extends JFrame {
    private String origen;
    private String destino;
    private Date fecha;
    private int cantidadPasajeros;
    private List<String> nombres;
    private List<String> apellidos;
    private ControladorReserva controlador;
    private List<JButton> asientosButtons;
    private List<String> asientosSeleccionados;
    private JLabel mensajeLabel;

    public VentanaAsientos(String origen, String destino, Date fecha, int cantidadPasajeros, List<String> nombres, List<String> apellidos, ControladorReserva controlador) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.cantidadPasajeros = cantidadPasajeros;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.controlador = controlador;
        this.asientosSeleccionados = new ArrayList<>();

        setTitle("Seleccionar Asientos");
        setSize(650, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        mensajeLabel = new JLabel("Seleccione " + cantidadPasajeros + " asiento(s)");
        mensajeLabel.setBounds(10, 10, 300, 25);
        panel.add(mensajeLabel);

        asientosButtons = new ArrayList<>();
        int filas = 10;
        int columnas = 4;
        int yOffset = 50;
        int xOffset = 10;

        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                JButton asientoButton = new JButton((char) ('A' + columna) + String.valueOf(fila + 1));
                asientoButton.setBounds(xOffset + (columna * 100), yOffset + (fila * 50), 90, 40);
                asientoButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        seleccionarAsiento(asientoButton);
                    }
                });
                panel.add(asientoButton);
                asientosButtons.add(asientoButton);
            }
        }

        JButton siguienteButton = new JButton("Siguiente");
        siguienteButton.setBounds(10, yOffset + (filas * 50) + 20, 100, 25);
        panel.add(siguienteButton);

        siguienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaResumen();
            }
        });
    }

    private void seleccionarAsiento(JButton asientoButton) {
        String asiento = asientoButton.getText();
        if (asientosSeleccionados.contains(asiento)) {
            asientosSeleccionados.remove(asiento);
            asientoButton.setEnabled(true);
        } else {
            if (asientosSeleccionados.size() < cantidadPasajeros) {
                asientosSeleccionados.add(asiento);
                asientoButton.setEnabled(false);
            } else {
                mensajeLabel.setText("Ya ha seleccionado todos los asientos necesarios.");
            }
        }
    }

    private void abrirVentanaResumen() {
        if (asientosSeleccionados.size() == cantidadPasajeros) {
            VentanaResumen ventanaResumen = new VentanaResumen(origen, destino, fecha, cantidadPasajeros, nombres, apellidos, asientosSeleccionados, controlador);
            ventanaResumen.setVisible(true);
            dispose();
        } else {
            mensajeLabel.setText("Por favor, seleccione todos los asientos necesarios.");
        }
    }
}
