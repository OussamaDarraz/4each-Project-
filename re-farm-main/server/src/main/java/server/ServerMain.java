package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.LoadProperties;

import java.io.IOException;
import java.sql.*;


public class ServerMain {
    static Logger logger =LoggerFactory.getLogger(ServerMain.class.getName());

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Server server = new Server();
        LoadProperties p = new LoadProperties("server.properties");
        int port = Integer.parseInt(p.getProp("port"));
        server.listen(port);
    }
}
