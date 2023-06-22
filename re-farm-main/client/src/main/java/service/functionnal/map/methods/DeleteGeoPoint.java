package service.functionnal.map.methods;

import service.network.Client;
import service.network.Request;
import service.network.Response;

import java.io.IOException;

public class DeleteGeoPoint {
    public static String run(Client cl, int id) throws IOException {
        Request selectReq = new Request("DELETE_GEO_POINT");
        selectReq.setParam("id", id);
        Response res = cl.call(selectReq);
        return res.getMessage();
    }
}
