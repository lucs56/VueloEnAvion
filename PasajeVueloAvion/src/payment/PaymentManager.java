package payment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentManager {

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/pasajevueloavion", "root", "");
    }

    public static boolean makePayment(String reservationId, double amount, String method, String status) {
        String sql = "INSERT INTO payments (reservation_id, amount, method, status) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, Integer.parseInt(reservationId));
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
