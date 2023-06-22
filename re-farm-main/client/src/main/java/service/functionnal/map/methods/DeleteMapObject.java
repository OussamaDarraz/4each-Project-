package service.functionnal.map.methods;

import service.network.Client;
import service.network.Request;
import service.network.Response;

import java.io.IOException;

public class DeleteMapObject {
    public static String run(Client cl, int id) throws IOException {
        Request selectReq = new Request("DELETE_MAP_OBJECT");
        selectReq.setParam("id", id);
        Response res = cl.call(selectReq);
        return res.getMessage();
    }
}
