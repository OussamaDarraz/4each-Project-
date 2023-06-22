package service.functionnal.map.methods;

import service.network.Client;
import service.network.Response;
import service.network.Request;
import java.io.IOException;

public class PostMapObject {
    public static Response run(Client cl, String label, String descr, String type) throws IOException {
        Request selectReq = new Request("POST_MAP_OBJECT");
        selectReq.setParam("label", label);
        selectReq.setParam("descr", descr);
        selectReq.setParam("type", type);
        Response res = cl.call(selectReq);
        return res;
    }
}
