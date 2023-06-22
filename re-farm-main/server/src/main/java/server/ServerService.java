package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.util.stream.Stream;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import connectionPool.DataSource;
import business.Router;

public class ServerService extends Thread {
    private final Logger logger = LoggerFactory.getLogger(ServerService.class.getName());

    private final Socket socket;
    private final Connection connection;
    private boolean run;
    private boolean DEMO_MODE = true;

    public ServerService(Socket socket) {
        this.socket = socket;
        connection = DataSource.getConnection();
        logger.info("[" + getName() + "] is connected.");
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Stream<String> st = reader.lines();
            st.forEach(reqJsonString -> {
                Request req = null;
                try {
                    req = new Request(reqJsonString, connection);
                    Response res = Router.go(req);
                    String resJsonString = res.toJson();
                    if (DEMO_MODE) demo(reqJsonString, res);
                    else logger.info(reqJsonString + " --> " + resJsonString);
                    writer.println(resJsonString);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
            DataSource.returnConnection(connection);
            socket.close();
            logger.info(getName() + " as been disconnected.");
        } catch (IOException ex) {
            logger.info("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void demo(String req, Response res) throws JsonProcessingException {
        logger.info("REQUEST :");
        logger.info(req);
        logger.info("RESPONSE :");
        logger.info(res.toJsonDemo());
    }

}
