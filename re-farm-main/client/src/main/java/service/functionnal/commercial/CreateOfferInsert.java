package service.functionnal.commercial;

import service.network.Client;
import service.network.Request;
import service.network.Response;

import java.io.IOException;

public class CreateOfferInsert {


    public static Response define1 (Client cl, String np , int pd) throws IOException {

        Request insertReq = new Request("OFFER_CREATE_IN_1");
        insertReq.setParam("name_product",np );
        insertReq.setParam("poids", pd);

        Response res = cl.call(insertReq);
        return res;

    }


    public static String define2 (Client cl, String ref, String cat, String dat,String SelectItem) throws IOException {

            Request insertReq = new Request("OFFER_CREATE_IN_2");
            insertReq.setParam("reference", ref);
            insertReq.setParam("categorie", cat);
            insertReq.setParam("date_creation", dat);
            /*
            insertReq.setParam("date_expiration", "");
            insertReq.setParam("prix", );*/
            insertReq.setParam("id_prod", SelectItem);

            Response res = cl.call(insertReq);
            return res.getStatus();

    }

}
