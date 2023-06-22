package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONObject;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collection;

public class Response {
    private ObjectMapper mapper = new ObjectMapper();
    private String[] cols;
    /*** VALS ***/
    private String status;
    private String message;
    ArrayNode dataNode;
    JSONObject jsonObject;


    public Response(String status) {
        jsonObject = new JSONObject();
        this.status = status;
    }

    public Response(String status, String message) {
        jsonObject = new JSONObject();
        this.status = status;
        this.message = message;
    }

    public String toJson() {
        ObjectNode root = mapper.createObjectNode();
        root.set("status", mapper.valueToTree(this.status));
        root.set("message", mapper.valueToTree(this.message));
        if (dataNode != null) root.set("data", dataNode);
        return root.toString();
    }

    public String toJsonDemo() throws JsonProcessingException {
        ObjectNode root = mapper.createObjectNode();
        root.set("status", mapper.valueToTree(this.status));
        root.set("message", mapper.valueToTree(this.message));
        if (dataNode != null) root.set("data", dataNode);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
    }

    public void dataSetToArray(ResultSet rs) throws SQLException, Exception {
        dataNode = mapper.createArrayNode();
        ResultSetMetaData meta = rs.getMetaData();
        int nbCol = meta.getColumnCount();
        cols = new String[nbCol];
        for (int i = 0; i < nbCol; i++) cols[i] = meta.getColumnName(i + 1);
        while (rs.next()) {
            ObjectNode obj = mapper.createObjectNode();
            for (int i = 0; i < nbCol; i++) {
                obj.put(cols[i], rs.getString(i + 1));
            }
            dataNode.add(obj);
        }
    }

    public void collectToArrayNode(Collection<?> collection) {
        this.dataNode = mapper.valueToTree(collection);
    }

    public void differenceBeteween(int res) {
        dataNode = mapper.createArrayNode();
        ObjectNode obj = mapper.createObjectNode();
        obj.put("Dif",res);
        dataNode.add(obj);
    }
    public void differenceBeteweenGust(int res) {
        dataNode = mapper.createArrayNode();
        ObjectNode obj = mapper.createObjectNode();
        obj.put("DifGust",res);
        dataNode.add(obj);
    }
    public void numberConfigured(int res) {
        dataNode = mapper.createArrayNode();
        ObjectNode obj = mapper.createObjectNode();
        obj.put("Number_config",res);
        dataNode.add(obj);
    }
    public void numberAllAnemo(int res) {
        dataNode = mapper.createArrayNode();
        ObjectNode obj = mapper.createObjectNode();
        obj.put("Number_all",res);
        dataNode.add(obj);
    }

    public void idAllpanel(int res) {
        dataNode = mapper.createArrayNode();
        ObjectNode obj = mapper.createObjectNode();
        obj.put("Id_pan",res);
        dataNode.add(obj);
    }
    public void totpanel(int res) {
        dataNode = mapper.createArrayNode();
        ObjectNode obj = mapper.createObjectNode();
        obj.put("totpan",res);
        dataNode.add(obj);
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
