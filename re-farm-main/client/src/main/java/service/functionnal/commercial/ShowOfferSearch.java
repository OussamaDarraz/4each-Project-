package service.functionnal.commercial;

import com.fasterxml.jackson.databind.JsonNode;
import service.network.Client;
import service.network.Request;
import service.network.Response;

import java.io.IOException;
import java.util.ArrayList;

public class ShowOfferSearch {
    public static ArrayList<String[]> sortSeek(Client cl,String referee) throws IOException {
        ArrayList<String[]> datom = new ArrayList<>();
        Request selectReq = new Request("OFFER_SHOW_SEEK");
        selectReq.setParam("reference",referee);
        Response res = cl.call(selectReq);

         String[] row;
        JsonNode dataNode = res.getDataNode();
        for (JsonNode node : dataNode) {

            row = new String[10];
            row[0] = node.path("reference").asText();
            row[1] = node.path("name_product").asText();
            row[2] = node.path("poids").asText();
            row[3] = node.path("categorie").asText();
            row[4] = node.path("cust_type").asText();
            row[5] = node.path("coefficient").asText();
            row[6] = node.path("date_creation").asText();
            row[7] = node.path("date_ligne").asText();
            row[8] = node.path("date_expiration").asText();
            row[9] = node.path("prix").asText();

            datom.add(row);
        }
        return datom;
    }
}

