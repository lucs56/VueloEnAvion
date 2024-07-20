package vueloenavion.ventanas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vueloenavion.controladores.ControladorUsuario;

public class VentanaInicioSesion extends JFrame {
    private JTextField emailText;
    private JPasswordField contraseñaText;
    private JLabel mensajeLabel;
    private ControladorUsuario controladorUsuario;

    public VentanaInicioSesion(ControladorUsuario controladorUsuario) {
        this.controladorUsuario = controladorUsuario;

        setTitle("Inicio de Sesión");
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

        JLabel contraseñaLabel = new JLabel("Contraseña:");
        contraseñaLabel.setBounds(10, 60, 150, 25);
        panel.add(contraseñaLabel);

        contraseñaText = new JPasswordField(20);
        contraseñaText.setBounds(170, 60, 200, 25);
        panel.add(contraseñaText);

        JButton ingresarButton = new JButton("Ingresar");
        ingresarButton.setBounds(10, 100, 150, 25);
        panel.add(ingresarButton);

        JButton registrarButton = new JButton("Registrar");
        registrarButton.setBounds(170, 100, 150, 25);
        panel.add(registrarButton);

        JButton recuperarButton = new JButton("Recuperar Contraseña");
        recuperarButton.setBounds(10, 140, 150, 25);
        panel.add(recuperarButton);

        mensajeLabel = new JLabel("");
        mensajeLabel.setBounds(10, 180, 360, 25);
        panel.add(mensajeLabel);

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaRegistroUsuario();
            }
        });

        recuperarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaRecuperacionContraseña();
            }
        });
    }

    private void iniciarSesion() {
        String email = emailText.getText();
        String contraseña = new String(contraseñaText.getPassword());

        boolean exito = controladorUsuario.verificarUsuario(email, contraseña);
        if (exito) {
            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
            ventanaPrincipal.setVisible(true);
            dispose();
        } else {
            mensajeLabel.setText("Email o contraseña incorrectos.");
        }
    }

    private void abrirVentanaRegistroUsuario() {
        VentanaRegistroUsuario ventanaRegistroUsuario = new VentanaRegistroUsuario(controladorUsuario);
        ventanaRegistroUsuario.setVisible(true);
        dispose();
    }

    private void abrirVentanaRecuperacionContraseña() {
        String email = emailText.getText();
        String preguntaSeguridad = controladorUsuario.obtenerPreguntaSeguridad(email);

        if (preguntaSeguridad != null) {
            VentanaRecuperacionContraseña ventanaRecuperacionContraseña = new VentanaRecuperacionContraseña(controladorUsuario);
            ventanaRecuperacionContraseña.setPreguntaSeguridad(preguntaSeguridad);
            ventanaRecuperacionContraseña.setVisible(true);
            dispose();
        } else {
            mensajeLabel.setText("Email no encontrado.");
        }
    }
}
