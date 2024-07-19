package vueloenavion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class VentanaResumen extends JFrame {
    private String origen;
    private String destino;
    private Date fecha;
    private int cantidadPasajeros;
    private List<String> nombres;
    private List<String> apellidos;
    private List<String> asientosSeleccionados;
    private ControladorReserva controlador;
    private JLabel mensajeLabel;

    public VentanaResumen(String origen, String destino, Date fecha, int cantidadPasajeros, List<String> nombres, List<String> apellidos, List<String> asientosSeleccionados, ControladorReserva controlador) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.cantidadPasajeros = cantidadPasajeros;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.asientosSeleccionados = asientosSeleccionados;
        this.controlador = controlador;

        setTitle("Resumen de la Reserva");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        String[] columnNames = {"Nombre", "Apellido", "Asiento"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (int i = 0; i < cantidadPasajeros; i++) {
            model.addRow(new Object[]{nombres.get(i), apellidos.get(i), asientosSeleccionados.get(i)});
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 760, 200);
        panel.add(scrollPane);

        JButton confirmarButton = new JButton("Confirmar Reserva");
        confirmarButton.setBounds(10, 230, 150, 25);
        panel.add(confirmarButton);

        mensajeLabel = new JLabel("");
        mensajeLabel.setBounds(10, 270, 760, 100);
        panel.add(mensajeLabel);

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarReserva();
            }
        });
    }

    private void confirmarReserva() {
        boolean exito = controlador.realizarReserva(origen, destino, fecha, cantidadPasajeros, nombres, apellidos, asientosSeleccionados);
        if (exito) {
            mensajeLabel.setText("Reserva realizada con éxito:");
            StringBuilder mensaje = new StringBuilder("<html>");
            mensaje.append("Origen: ").append(origen).append("<br>");
            mensaje.append("Destino: ").append(destino).append("<br>");
            mensaje.append("Fecha: ").append(fecha).append("<br>");
            mensaje.append("Pasajeros: ").append(cantidadPasajeros).append("<br>");
            for (int i = 0; i < cantidadPasajeros; i++) {
                mensaje.append("Pasajero ").append(i + 1).append(": ").append(nombres.get(i)).append(" ").append(apellidos.get(i)).append(" - Asiento: ").append(asientosSeleccionados.get(i)).append("<br>");
            }
            mensaje.append("</html>");
            mensajeLabel.setText(mensaje.toString());
        } else {
            mensajeLabel.setText("Error al realizar la reserva.");
        }
    }
}
