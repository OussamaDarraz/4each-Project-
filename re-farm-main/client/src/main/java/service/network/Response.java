package service.network;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONObject;

public class Response {
    private ObjectMapper mapper;
    private String[] cols;
    /*** VALS ***/
    private String status;
    private String message;
    ArrayNode dataNode;
    JSONObject jsonObject ;


    public Response(String json) throws JsonProcessingException {
        mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        this.status = root.path("status").asText();
        this.message = root.path("message").asText();
        this.dataNode = !root.path("data").isMissingNode() ? (ArrayNode) root.path("data") : null;
    }

    public String toJson() throws JsonProcessingException {
        ObjectNode root = mapper.createObjectNode();
        root.set("status", mapper.valueToTree(this.status));
        root.set("message", mapper.valueToTree(this.message));
        if(dataNode != null) root.set("data", dataNode);
        return root.toString();
        //return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
    }

    public String toJsonDemo() throws JsonProcessingException {
        ObjectNode root = mapper.createObjectNode();
        root.set("status", mapper.valueToTree(this.status));
        root.set("message", mapper.valueToTree(this.message));
        if(dataNode != null) root.set("data", dataNode);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
    }

    public String getStatus() {
        return this.status;
    }
    public String getMessage() { return this.message; }
    public ArrayNode getDataNode() {
        return dataNode;
    }
}
