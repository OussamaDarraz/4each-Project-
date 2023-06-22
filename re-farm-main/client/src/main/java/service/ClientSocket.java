package service;

import service.network.Client;
import service.network.Request;
import service.network.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ClientSocket {
    static final Logger LOGGER = LoggerFactory.getLogger(ClientSocket.class);
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        Request request = new Request("DIFF_GUST");
        request.setParam("object_label", "anemo 1" );
        Response res = client.call(request);
        LOGGER.info("Data = {}", res.toString());


    }
}
