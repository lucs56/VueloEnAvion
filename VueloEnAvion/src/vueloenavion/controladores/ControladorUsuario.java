package vueloenavion.controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import vueloenavion.Conexion;

public class ControladorUsuario {

    public boolean registrarUsuario(String nombre, String apellido, String email, String telefono, String direccion, String contraseña, String preguntaSeguridad, String respuestaSeguridad) {
        String sql = "INSERT INTO usuarios (nombre, apellido, email, telefono, direccion, contraseña, pregunta_seguridad, respuesta_seguridad) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = Conexion.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setString(3, email);
            statement.setString(4, telefono);
            statement.setString(5, direccion);
            statement.setString(6, contraseña);
            statement.setString(7, preguntaSeguridad);
            statement.setString(8, respuestaSeguridad);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean verificarUsuario(String email, String contraseña) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND contraseña = ?";
        
        try (Connection connection = Conexion.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, contraseña);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Si hay un resultado, las credenciales son correctas
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String obtenerPreguntaSeguridad(String email) {
        String sql = "SELECT pregunta_seguridad FROM usuarios WHERE email = ?";
        
        try (Connection connection = Conexion.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("pregunta_seguridad");
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean verificarRespuestaSeguridad(String email, String respuestaSeguridad) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND respuesta_seguridad = ?";
        
        try (Connection connection = Conexion.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, respuestaSeguridad);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarContraseña(String email, String nuevaContraseña) {
        String sql = "UPDATE usuarios SET contraseña = ? WHERE email = ?";
        
        try (Connection connection = Conexion.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nuevaContraseña);
            statement.setString(2, email);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
