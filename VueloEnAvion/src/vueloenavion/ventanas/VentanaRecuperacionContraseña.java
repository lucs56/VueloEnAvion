package vueloenavion.ventanas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vueloenavion.controladores.ControladorUsuario;
import vueloenavion.ventanas.VentanaInicioSesion;

public class VentanaRecuperacionContraseña extends JFrame {
    private JTextField emailText;
    private JTextField respuestaText;
    private JPasswordField nuevaContraseñaText;
    private JLabel preguntaLabel;
    private JLabel mensajeLabel;
    private ControladorUsuario controlador;

    public VentanaRecuperacionContraseña(ControladorUsuario controlador) {
        this.controlador = controlador;

        setTitle("Recuperación de Contraseña");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 20, 150, 25);
        panel.add(emailLabel);

        emailText = new JTextField(20);
        emailText.setBounds(170, 20, 200, 25);
        panel.add(emailText);

        preguntaLabel = new JLabel("Pregunta de Seguridad:");
        preguntaLabel.setBounds(10, 60, 360, 25);
        panel.add(preguntaLabel);

        respuestaText = new JTextField(20);
        respuestaText.setBounds(170, 100, 200, 25);
        panel.add(respuestaText);

        JLabel nuevaContraseñaLabel = new JLabel("Nueva Contraseña:");
        nuevaContraseñaLabel.setBounds(10, 140, 150, 25);
        panel.add(nuevaContraseñaLabel);

        nuevaContraseñaText = new JPasswordField(20);
        nuevaContraseñaText.setBounds(170, 140, 200, 25);
        panel.add(nuevaContraseñaText);

        JButton recuperarButton = new JButton("Recuperar");
        recuperarButton.setBounds(10, 180, 150, 25);
        panel.add(recuperarButton);

        mensajeLabel = new JLabel("");
        mensajeLabel.setBounds(10, 220, 360, 25);
        panel.add(mensajeLabel);

        recuperarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recuperarContraseña();
            }
        });
    }

    private void recuperarContraseña() {
        String email = emailText.getText();
        String respuesta = respuestaText.getText();
        String nuevaContraseña = new String(nuevaContraseñaText.getPassword());

        if (controlador.verificarRespuestaSeguridad(email, respuesta)) {
            boolean exito = controlador.actualizarContraseña(email, nuevaContraseña);
            if (exito) {
                mensajeLabel.setText("Contraseña actualizada con éxito. Por favor, inicie sesión.");
                VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion(controlador);
                ventanaInicioSesion.setVisible(true);
                dispose();
            } else {
                mensajeLabel.setText("Error al actualizar la contraseña.");
            }
        } else {
            mensajeLabel.setText("Respuesta de seguridad incorrecta.");
        }
    }

    public void setPreguntaSeguridad(String pregunta) {
        preguntaLabel.setText("Pregunta de Seguridad: " + pregunta);
    }
}
