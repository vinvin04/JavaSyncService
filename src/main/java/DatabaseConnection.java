import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    private static String url = ""; //jdbc:mysql://localhost:3306/sync
    private static String username = ""; //root
    private static String password = ""; //root

    private DatabaseConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public  static void init(String url1, String username1, String password1) {
        System.out.println("in db singleton" +url + " "+username + " "+ password);
        url = url1;
        username = username1;
        password = password1;
        System.out.println("in db singleton" + url + " "+username + " "+ password);
    }

    public static DatabaseConnection getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }

        return instance;
    }
}
