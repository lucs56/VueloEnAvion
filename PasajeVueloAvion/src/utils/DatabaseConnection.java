package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/pasajevueloavion"; // Actualiza el URL si es necesario
    private static final String USER = "root"; // Reemplaza con tu usuario de MySQL
    private static final String PASSWORD = ""; // Reemplaza con tu contraseña de MySQL

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

