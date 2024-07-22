import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/pasajevueloavion";
        String user = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Connection failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
