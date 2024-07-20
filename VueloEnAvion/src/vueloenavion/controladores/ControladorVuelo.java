package vueloenavion.controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vueloenavion.Conexion;
import vueloenavion.ventanas.Vuelo;

public class ControladorVuelo {

    public List<Vuelo> buscarVuelos(String origen, String destino, java.sql.Date fechaSalida, java.sql.Date fechaLlegada, int pasajeros) {
        List<Vuelo> vuelos = new ArrayList<>();
        String sql = "SELECT * FROM vuelos WHERE origen = ? AND destino = ? AND fecha_salida = ? AND fecha_llegada = ?";

        try (Connection connection = Conexion.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, origen);
            statement.setString(2, destino);
            statement.setDate(3, fechaSalida);
            statement.setDate(4, fechaLlegada);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Vuelo vuelo = new Vuelo();
                vuelo.setAerolinea(resultSet.getString("aerolinea"));
                vuelo.setOrigen(resultSet.getString("origen"));
                vuelo.setDestino(resultSet.getString("destino"));
                vuelo.setFechaSalida(resultSet.getDate("fecha_salida"));
                vuelo.setHoraSalida(resultSet.getTime("hora_salida"));
                vuelo.setFechaLlegada(resultSet.getDate("fecha_llegada"));
                vuelo.setHoraLlegada(resultSet.getTime("hora_llegada"));
                vuelo.setDuracion(resultSet.getTime("duracion"));
                vuelo.setPrecio(resultSet.getDouble("precio"));

                vuelos.add(vuelo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vuelos;
    }
}
