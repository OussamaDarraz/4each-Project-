package connectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static ConnectionPool pool;

    public static void start() {
        try {
            pool = new ConnectionPool();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return pool.getConnection();
    }

    public static void returnConnection(Connection connect) {
        pool.returnConnection(connect);
    }

    public static void closePool() throws SQLException {
        pool.closeConnections();
    }


}
