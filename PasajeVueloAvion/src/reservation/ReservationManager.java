package reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationManager {

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/pasajevueloavion", "root", "");
    }

    // Método para hacer una reserva
    public static boolean makeReservation(int userId, String flightId, String name, String birthdate, String passport) {
        String sql = "INSERT INTO reservations (user_id, flight_id, passenger_name, passenger_birthdate, passenger_passport) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, flightId);
            pstmt.setString(3, name);
            pstmt.setString(4, birthdate);
            pstmt.setString(5, passport);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener el historial de reservas
    public static String getReservationHistory(String userEmail) throws SQLException {
        String sql = "SELECT * FROM reservations WHERE user_id = (SELECT id FROM users WHERE email = ?)";
        StringBuilder history = new StringBuilder();
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, userEmail);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                history.append("Reservation ID: ").append(rs.getInt("id")).append("\n");
                history.append("Flight ID: ").append(rs.getString("flight_id")).append("\n");
                history.append("Passenger Name: ").append(rs.getString("passenger_name")).append("\n");
                history.append("Birthdate: ").append(rs.getDate("passenger_birthdate")).append("\n");
                history.append("Passport: ").append(rs.getString("passenger_passport")).append("\n");
                history.append("---------\n");
            }
        }
        return history.toString();
    }

    // Método para obtener vuelos (esto asume que hay una tabla de vuelos)
    public static ResultSet getFlights() throws SQLException {
        String sql = "SELECT * FROM flights";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);
        return pstmt.executeQuery();
    }

    // Método para realizar un pago
    public static boolean makePayment(int reservationId, double amount, String method, String status) {
        String sql = "INSERT INTO payments (reservation_id, amount, method, status) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, reservationId);
            pstmt.setDouble(2, amount);
            pstmt.setString(3, method);
            pstmt.setString(4, status);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
