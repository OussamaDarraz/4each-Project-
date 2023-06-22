package methods;

import com.fasterxml.jackson.core.JsonProcessingException;
import network.Client;
import network.Request;
import network.Response;

import java.io.IOException;

public class Insert {
    public static Response run(Client cl, String label, String descr, String type) throws IOException {
        Request selectReq = new Request("POST_MAP_OBJECT");
        selectReq.setParam("label", label);
        selectReq.setParam("descr", descr);
        selectReq.setParam("type", type);
        Response res = cl.call(selectReq);
        return res;
    }
}
