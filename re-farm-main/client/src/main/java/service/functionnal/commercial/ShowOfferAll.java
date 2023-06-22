package service.functionnal.commercial;

import service.network.Client;
import service.network.Request;
import service.network.Response;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;

public class ShowOfferAll {

    public static ArrayList<String[]> run(Client cl) throws IOException {
        ArrayList<String[]> data = new ArrayList<>();
        Request selectReq = new Request("OFFER_SHOW_ALL");
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

                data.add(row);
            }
            return data;
        }
    }

