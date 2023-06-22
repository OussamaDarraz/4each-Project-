package service.functionnal.commercial;

import service.network.Client;
import service.network.Request;
import service.network.Response;

import java.io.IOException;
import java.util.ArrayList;

public class ProdOfferAll {
    public static ArrayList<String[]> run(Client cl) throws IOException {
        String[] row;
        ArrayList<String[]> data = new ArrayList<>();
        Request selectReq = new Request("OFFER_PROD_ALL");
        Response res = cl.call(selectReq);


        return data;
    }
}
