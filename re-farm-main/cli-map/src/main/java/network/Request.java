package network;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Request {
    private ObjectMapper mapper;
    private String process;
    private ObjectNode params;
    private ArrayNode params1;

    public Request(String process) {
        mapper = new ObjectMapper();
        this.process = process;
    }

    public void setParam(String key, String value) {
        if (params == null) params = mapper.createObjectNode();
        params.put(key, value);
    }

    public void setParam(String key, int value) {
        if (params == null) params = mapper.createObjectNode();
        params.put(key, value);
    }

    public void setParam(String key, double value) {
        if (params == null) params = mapper.createObjectNode();
        params.put(key, value);
    }
    public String toJson() throws JsonProcessingException {
        ObjectNode root = mapper.createObjectNode();
        root.put("process", process);
        if (params != null) root.set("params", params);
        return root.toString();
    }

    public <T> void parsePayload(T payload) {
        this.params = mapper.convertValue(payload, ObjectNode.class);
    }
    public <E> void parsePayload1(E payload) {
        /*TypeReference<List<E>> tRef = new TypeReference<List<E>> () {};
        this.params = (ObjectNode) mapper.convertValue(payload, tRef);*/
        //AnemometreValue anemometreValue = mapper.convertValue(payload, AnemometreValue.class);
        this.params1 = mapper.valueToTree(payload);

    }


}
