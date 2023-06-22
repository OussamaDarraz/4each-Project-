package service.functionnal.commercial;

import service.network.Client;
import service.network.Request;
import service.network.Response;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;

public class SpecOfferAll {

    public static ArrayList<String[]> run(Client cl) throws IOException {
        String[] row;
        ArrayList<String[]> data = new ArrayList<>();
        Request selectReq = new Request("OFFER_SPEC_ALL");
        Response res = cl.call(selectReq);

        JsonNode dataNode = res.getDataNode();
        for (JsonNode node : dataNode) {

            row = new String[6];
            row[0] = node.path("reference").asText();
            row[1] = node.path("cust_type").asText();
            row[2] = node.path("coefficient").asText();
            row[3] = node.path("date_ligne").asText();
            row[4] = node.path("date_expiration").asText();
            row[5] = node.path("prix").asText();

            data.add(row);
        }
        return data;
    }
}
