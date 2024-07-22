package flight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightManager {
    private static final String URL = "jdbc:mysql://localhost:3306/pasajevueloavion";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static ResultSet getFlights() throws SQLException {
        Connection connection = getConnection();
        String sql = "SELECT * FROM flights";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        return pstmt.executeQuery();
    }
}
