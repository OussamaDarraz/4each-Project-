package service.functionnal.commercial;

import service.network.Client;
import service.network.Request;
import service.network.Response;

import java.io.IOException;

public class SpecOfferUpdate {


    public static String rollUp1(Client cl, String typo, String ceff, String rf) throws IOException {
        Request updateReq = new Request("OFFER_SPEC_UP_1");
        updateReq.setParam("cust_type", typo);
        updateReq.setParam("coefficient", ceff);
        updateReq.setParam("reference", rf);

        Response res = cl.call(updateReq);
        return res.getStatus();
    }



    public static String rollUp2(Client cl, String dati1, String dati2, String cost,String reff) throws IOException {
        Request updateReq = new Request("OFFER_SPEC_UP_2");
        updateReq.setParam("date_ligne", dati1);
        updateReq.setParam("date_expiration", dati2);
        updateReq.setParam("prix", cost);
        updateReq.setParam("reference", reff);


        Response res = cl.call(updateReq);
        return res.getMessage();
    }


}
