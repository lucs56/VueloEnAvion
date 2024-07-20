package vueloenavion.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PruebaConexion {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/usuariovuelo";
        String usuario = "root";
        String contraseña = ""; // Ajusta la contraseña si es diferente

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            if (conexion != null) {
                System.out.println("Conexión exitosa a la base de datos.");
                conexion.close();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos.");
            e.printStackTrace();
        }
    }
}
