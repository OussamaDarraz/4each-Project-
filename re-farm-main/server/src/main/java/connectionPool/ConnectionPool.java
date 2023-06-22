package connectionPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.LoadProperties;

import java.io.IOException;
import java.sql.*;
import java.util.Stack;

public class ConnectionPool {
    static Logger logger = LoggerFactory.getLogger(ConnectionPool.class);
    private static final int nbConnection = 20;
    // private static final boolean autoGenerateMoreConnections = false;
    Stack<Connection> collection;

    public ConnectionPool() throws IOException, ClassNotFoundException, SQLException {
        collection = new Stack();
        generateConnections(nbConnection);
    }

    public Connection getConnection() {
        try {
            return collection.pop();
        } catch (Exception e) {
            logger.info("Toutes les connexions sont déjà occupées.");
        }
        return null;
    }

    public void returnConnection(Connection connect) {
        collection.push(connect);
    }

    public void closeConnections() throws SQLException {
        for (Connection co : collection) {
            co.close();
        }
    }

    private void generateConnections(int nbConnection) throws IOException, ClassNotFoundException, SQLException {
        LoadProperties p = new LoadProperties("poolconnection.properties");
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://" + p.getProp("host") + ":" + p.getProp("port") + "/" + p.getProp("database");
        for (int i = 0; i < nbConnection; i++) {
            collection.push(DriverManager.getConnection(url, p.getProp("user"), p.getProp("password")));
        }
    }
}
