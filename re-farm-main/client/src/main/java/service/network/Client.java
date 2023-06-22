package service.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class Client {
    private static final int port = 9090;
    //private static final String hostname = "172.31.254.54";
   private static final String  hostname = "localhost";
    private Socket socket;
    private PrintWriter writer;

    public Client() {
        try {
            if(Objects.isNull(socket))
            socket = new Socket(hostname, port);
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Response call(Request req) throws IOException {
        String reqJson = req.toJson();
        writer.println(reqJson);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String resJson = reader.readLine();
        System.out.println(resJson);
        return new Response(resJson);
    }

    public void closeSocket() throws IOException {
        socket.close();
    }
}
