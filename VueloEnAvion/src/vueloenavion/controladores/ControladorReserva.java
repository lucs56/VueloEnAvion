package vueloenavion.controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import vueloenavion.Conexion;

public class ControladorReserva {

    public boolean guardarReserva(String origen, String destino, Date fecha, int cantidadPasajeros, List<String> nombres, List<String> apellidos, List<String> asientosSeleccionados) {
        String sqlReserva = "INSERT INTO reservas (origen, destino, fecha, cantidad_pasajeros) VALUES (?, ?, ?, ?)";
        String sqlPasajero = "INSERT INTO pasajeros (nombre, apellido, reserva_id) VALUES (?, ?, ?)";
        String sqlAsiento = "INSERT INTO asientos (asiento, reserva_id) VALUES (?, ?)";

        try (Connection connection = Conexion.obtenerConexion();
             PreparedStatement statementReserva = connection.prepareStatement(sqlReserva, PreparedStatement.RETURN_GENERATED_KEYS);
             PreparedStatement statementPasajero = connection.prepareStatement(sqlPasajero);
             PreparedStatement statementAsiento = connection.prepareStatement(sqlAsiento)) {

            // Insertar la reserva
            statementReserva.setString(1, origen);
            statementReserva.setString(2, destino);
            statementReserva.setDate(3, new java.sql.Date(fecha.getTime()));
            statementReserva.setInt(4, cantidadPasajeros);
            statementReserva.executeUpdate();

            // Obtener el ID de la reserva insertada
            ResultSet generatedKeys = statementReserva.getGeneratedKeys();
            int reservaId = 0;
            if (generatedKeys.next()) {
                reservaId = generatedKeys.getInt(1);
            } else {
                System.out.println("No se pudo obtener el ID de la reserva.");
                return false;
            }

            // Insertar los pasajeros
            for (int i = 0; i < cantidadPasajeros; i++) {
                statementPasajero.setString(1, nombres.get(i));
                statementPasajero.setString(2, apellidos.get(i));
                statementPasajero.setInt(3, reservaId);
                statementPasajero.executeUpdate();
            }

            // Insertar los asientos seleccionados
            for (String asiento : asientosSeleccionados) {
                statementAsiento.setString(1, asiento);
                statementAsiento.setInt(2, reservaId);
                statementAsiento.executeUpdate();
            }

            System.out.println("Reserva y detalles guardados con éxito.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean realizarReserva(String origen, String destino, Date fecha, int cantidadPasajeros, List<String> nombres, List<String> apellidos, List<String> asientosSeleccionados) {
        return guardarReserva(origen, destino, fecha, cantidadPasajeros, nombres, apellidos, asientosSeleccionados);
    }
}
