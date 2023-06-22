package service.functionnal.commercial;

import service.network.Client;
import service.network.Request;
import service.network.Response;

import java.io.IOException;

public class ShowOfferDelete {
    private  final static Client CLIENT= new Client();

    public static String  rm1(String reffe) throws IOException {

        Request deleteReq = new Request("OFFER_SHOW_DEL_1");
        deleteReq.setParam("reference", reffe);
        Response res1 = CLIENT.call(deleteReq);
        return res1.getStatus();
    }
    public static String rm2(String nprd) throws IOException {

        Request deleteReq = new Request("OFFER_SHOW_DEL_2");
        deleteReq.setParam("cust_type", nprd);
        Response res2 = CLIENT.call(deleteReq);
        return res2.getStatus();
    }
    public static String rm3(String npd) throws IOException {
        Request deleteReq = new Request("OFFER_SHOW_DEL_3");
        deleteReq.setParam("name_product",npd);

        Response res3 = CLIENT.call(deleteReq);
        return res3.getStatus();
    }


}