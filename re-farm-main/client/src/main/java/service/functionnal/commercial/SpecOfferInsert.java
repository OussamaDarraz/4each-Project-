package service.functionnal.commercial;

import service.network.Client;
import service.network.Request;
import service.network.Response;

import java.io.IOException;

public class SpecOfferInsert {

    public static Response round1(Client cl, String tp, String coef) throws IOException {

        Request insertReq = new Request("OFFER_SPEC_IN_1");
        insertReq.setParam("cust_type", tp);
        insertReq.setParam("coefficient", coef);

        Response res = cl.call(insertReq);
        return res;

    }
    public static String round2(Client cl, String dat1, String dat2,int prx, int po, String ref) throws IOException {

            Request insertReq = new Request("OFFER_SPEC_IN_2");
            insertReq.setParam("date_ligne", dat1);
            insertReq.setParam("date_expiration", dat2);
            insertReq.setParam("prix", prx);
            insertReq.setParam("id_customer", po);
            insertReq.setParam("reference", ref);


            Response res = cl.call(insertReq);

        return res.getStatus();
    }



}
