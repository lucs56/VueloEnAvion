package vueloenavion;

import javax.swing.*;
import vueloenavion.controladores.ControladorUsuario;
import vueloenavion.ventanas.VentanaInicioSesion;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ControladorUsuario controladorUsuario = new ControladorUsuario();
            VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion(controladorUsuario);
            ventanaInicioSesion.setVisible(true);
        });
    }
}
