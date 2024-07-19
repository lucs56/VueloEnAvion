
package vueloenavion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ControladorReserva {
    public boolean guardarReserva(String origen, String destino, Date fecha, int cantidadPasajeros, List<String> nombres, List<String> apellidos, List<String> asientosSeleccionados) {
        String sql = "INSERT INTO reservas (origen, destino, fecha, cantidad_pasajeros) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuariovuelo", "tu_usuario", "tu_contraseña");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, origen);
            statement.setString(2, destino);
            statement.setDate(3, new java.sql.Date(fecha.getTime()));
            statement.setInt(4, cantidadPasajeros);
            statement.executeUpdate();

            // Guardar los nombres y apellidos de los pasajeros
            for (int i = 0; i < cantidadPasajeros; i++) {
                String nombre = nombres.get(i);
                String apellido = apellidos.get(i);
                sql = "INSERT INTO pasajeros (nombre, apellido) VALUES (?, ?)";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, nombre);
                    ps.setString(2, apellido);
                    ps.executeUpdate();
                }
            }

            // Guardar los asientos seleccionados
            for (String asiento : asientosSeleccionados) {
                sql = "INSERT INTO asientos (asiento) VALUES (?)";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, asiento);
                    ps.executeUpdate();
                }
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    boolean guardarAsientos(List<String> asientosSeleccionados) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean realizarReserva(String origen, String destino, Date fecha, int cantidadPasajeros, List<String> nombres, List<String> apellidos, List<String> asientosSeleccionados) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
