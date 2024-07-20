package vueloenavion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/usuariovuelo";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "";

    public static Connection obtenerConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
