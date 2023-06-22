package service.functionnal.map.methods;

import service.network.Client;
import service.network.Request;
import service.network.Response;

import java.io.IOException;

public class UpdateMapObject {
    public static String run(Client cl, int id, String label, String descr, String type) throws IOException {
        Request selectReq = new Request("UPDATE_MAP_OBJECT");
        selectReq.setParam("id", id);
        selectReq.setParam("label", label);
        selectReq.setParam("descr", descr);
        selectReq.setParam("type", type);
        Response res = cl.call(selectReq);
        return res.getMessage();
    }
}
