package server;

import connectionPool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
   private static int port = 9090;
    public static boolean exit = false;
    private Logger logger = LoggerFactory.getLogger(Server.class.getName());


    public void listen(int port) {
        try (ServerSocket ss = new ServerSocket(port)) {
            DataSource.start();
            logger.info("Server TCP Started on " + port);
            while (!ss.isClosed()) {
                Socket socket = ss.accept();
                ServerService ses = new ServerService(socket);
                ses.start();
            }
            logger.info("Multi client server disabled");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
