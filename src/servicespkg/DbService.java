package servicespkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbService {

    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/scart";
    static String unicode = "?useUnicode=yes&characterEncoding=UTF-8";
    static String username = "root";
    static String password = "root";

    private static Connection connection;

    public DbService() throws SQLException {
    }

    static {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url + unicode, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public void finalize() {
        try {
            if (connection != null) {
                this.connection.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
