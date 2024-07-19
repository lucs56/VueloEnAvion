package vueloenavion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VentanaPasajeros extends JFrame {
    private List<JTextField> nombresText;
    private List<JTextField> apellidosText;
    private JLabel mensajeLabel;
    private String origen;
    private String destino;
    private Date fecha;
    private int cantidadPasajeros;
    private ControladorReserva controlador;

    public VentanaPasajeros(String origen, String destino, Date fecha, int cantidadPasajeros, ControladorReserva controlador) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.cantidadPasajeros = cantidadPasajeros;
        this.controlador = controlador;

        setTitle("Datos de los Pasajeros");
        setSize(500, 400 + (cantidadPasajeros * 70));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        nombresText = new ArrayList<>();
        apellidosText = new ArrayList<>();

        int yOffset = 20;

        for (int i = 0; i < cantidadPasajeros; i++) {
            JLabel nombreLabel = new JLabel("Nombre Pasajero " + (i + 1) + ":");
            nombreLabel.setBounds(10, yOffset, 150, 25);
            panel.add(nombreLabel);

            JTextField nombreText = new JTextField(20);
            nombreText.setBounds(170, yOffset, 200, 25);
            panel.add(nombreText);
            nombresText.add(nombreText);

            JLabel apellidoLabel = new JLabel("Apellido Pasajero " + (i + 1) + ":");
            apellidoLabel.setBounds(10, yOffset + 30, 150, 25);
            panel.add(apellidoLabel);

            JTextField apellidoText = new JTextField(20);
            apellidoText.setBounds(170, yOffset + 30, 200, 25);
            panel.add(apellidoText);
            apellidosText.add(apellidoText);

            yOffset += 70;
        }

        JButton siguienteButton = new JButton("Siguiente");
        siguienteButton.setBounds(10, yOffset, 100, 25);
        panel.add(siguienteButton);

        mensajeLabel = new JLabel("");
        mensajeLabel.setBounds(10, yOffset + 40, 460, 25);
        panel.add(mensajeLabel);

        siguienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaAsientos();
            }
        });
    }

    private void abrirVentanaAsientos() {
        List<String> nombres = new ArrayList<>();
        List<String> apellidos = new ArrayList<>();
        boolean datosCompletos = true;

        for (int i = 0; i < cantidadPasajeros; i++) {
            String nombre = nombresText.get(i).getText();
            String apellido = apellidosText.get(i).getText();
            if (nombre.isEmpty() || apellido.isEmpty()) {
                datosCompletos = false;
                break;
            }
            nombres.add(nombre);
            apellidos.add(apellido);
        }

        if (!datosCompletos) {
            mensajeLabel.setText("Por favor, ingrese el nombre y apellido de todos los pasajeros.");
            return;
        }

        VentanaAsientos ventanaAsientos = new VentanaAsientos(origen, destino, fecha, cantidadPasajeros, nombres, apellidos, controlador);
        ventanaAsientos.setVisible(true);
        dispose();
    }
}
