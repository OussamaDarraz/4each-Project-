package service.functionnal.map.methods;

import service.network.Client;
import service.network.Request;
import service.network.Response;

import java.io.IOException;

public class PostGeoPoint {
    public static Response run(Client cl, int id, int x, int y) throws IOException {
        Request selectReq = new Request("POST_GEO_POINT");
        selectReq.setParam("id", id);
        selectReq.setParam("x", x);
        selectReq.setParam("y", y);
        Response res = cl.call(selectReq);
        return res;
    }
}
