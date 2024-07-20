package vueloenavion.ventanas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vueloenavion.controladores.ControladorUsuario;
import vueloenavion.ventanas.VentanaInicioSesion;

public class VentanaRegistroUsuario extends JFrame {
    private JTextField nombreText;
    private JTextField apellidoText;
    private JTextField emailText;
    private JTextField telefonoText;
    private JTextField direccionText;
    private JPasswordField contraseñaText;
    private JTextField preguntaText;
    private JTextField respuestaText;
    private JLabel mensajeLabel;
    private ControladorUsuario controlador;

    public VentanaRegistroUsuario(ControladorUsuario controlador) {
        this.controlador = controlador;

        setTitle("Registro de Usuario");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(10, 20, 150, 25);
        panel.add(nombreLabel);

        nombreText = new JTextField(20);
        nombreText.setBounds(170, 20, 200, 25);
        panel.add(nombreText);

        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setBounds(10, 60, 150, 25);
        panel.add(apellidoLabel);

        apellidoText = new JTextField(20);
        apellidoText.setBounds(170, 60, 200, 25);
        panel.add(apellidoText);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 100, 150, 25);
        panel.add(emailLabel);

        emailText = new JTextField(20);
        emailText.setBounds(170, 100, 200, 25);
        panel.add(emailText);

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setBounds(10, 140, 150, 25);
        panel.add(telefonoLabel);

        telefonoText = new JTextField(20);
        telefonoText.setBounds(170, 140, 200, 25);
        panel.add(telefonoText);

        JLabel direccionLabel = new JLabel("Dirección:");
        direccionLabel.setBounds(10, 180, 150, 25);
        panel.add(direccionLabel);

        direccionText = new JTextField(20);
        direccionText.setBounds(170, 180, 200, 25);
        panel.add(direccionText);

        JLabel contraseñaLabel = new JLabel("Contraseña:");
        contraseñaLabel.setBounds(10, 220, 150, 25);
        panel.add(contraseñaLabel);

        contraseñaText = new JPasswordField(20);
        contraseñaText.setBounds(170, 220, 200, 25);
        panel.add(contraseñaText);

        JLabel preguntaLabel = new JLabel("Pregunta de Seguridad:");
        preguntaLabel.setBounds(10, 260, 150, 25);
        panel.add(preguntaLabel);

        preguntaText = new JTextField(20);
        preguntaText.setBounds(170, 260, 200, 25);
        panel.add(preguntaText);

        JLabel respuestaLabel = new JLabel("Respuesta de Seguridad:");
        respuestaLabel.setBounds(10, 300, 150, 25);
        panel.add(respuestaLabel);

        respuestaText = new JTextField(20);
        respuestaText.setBounds(170, 300, 200, 25);
        panel.add(respuestaText);

        JButton registrarButton = new JButton("Registrar");
        registrarButton.setBounds(10, 340, 150, 25);
        panel.add(registrarButton);

        mensajeLabel = new JLabel("");
        mensajeLabel.setBounds(10, 370, 360, 25);
        panel.add(mensajeLabel);

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });
    }

    private void registrarUsuario() {
        String nombre = nombreText.getText();
        String apellido = apellidoText.getText();
        String email = emailText.getText();
        String telefono = telefonoText.getText();
        String direccion = direccionText.getText();
        String contraseña = new String(contraseñaText.getPassword());
        String preguntaSeguridad = preguntaText.getText();
        String respuestaSeguridad = respuestaText.getText();

        boolean exito = controlador.registrarUsuario(nombre, apellido, email, telefono, direccion, contraseña, preguntaSeguridad, respuestaSeguridad);
        if (exito) {
            mensajeLabel.setText("Registro exitoso. Por favor, inicie sesión.");
            VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion(controlador);
            ventanaInicioSesion.setVisible(true);
            dispose();
        } else {
            mensajeLabel.setText("Error al registrar el usuario.");
        }
    }
}
