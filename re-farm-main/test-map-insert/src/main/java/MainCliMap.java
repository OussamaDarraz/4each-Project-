import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import methods.Insert;
import methods.InsertGeoPoint;
import network.Client;
import network.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainCliMap {
    static Logger logger = LoggerFactory.getLogger(MainCliMap.class.getName());
    private static Client client = new Client();

    private static String filePath = "C:\\Users\\oussa\\Desktop\\clement.json";
    private static int testId;

    public static void main(String[] args) throws IOException {
        //String[] args = {"1", "C:\\Users\\oussa\\Desktop\\clement.json"};
        logger.info("** ** TEST TECHNIQUE - PDS - CLEMENT MOULY ** **");
        if (args.length > 0) {
            testId = Integer.parseInt(args[0]);
            if (args.length > 1) filePath = args[1];
            // Connection to the server
            start();
        } else {
            logger.error("Minimum one argument.");
        }
        try {
            client.closeSocket();
            logger.info("Client disconnected.");
        } catch (Exception e) {
            logger.error("Cannot close socket bc it's null.");
        }
    }


    private static void start() throws IOException {
        try {
            // Read the json file
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(new File(filePath)).get("data");
            logger.info(json.toString());

            JsonNode node = getGoodNode(json, testId);
            // Defines var
            String label = node.path("label").asText();
            String descr = node.path("descr").asText();
            String type = node.path("type").asText();
            int x = node.path("x").asInt();
            int y = node.path("y").asInt();

            Response res = null;
            if (label != null && descr != null && type != null)
                res = Insert.run(client, label, descr, type);
            else
                logger.error("One of the params is null.");

            if (res.getStatus().equals("succes"))
                logger.info(res.getMessage());
            else
                logger.error(res.getMessage());
            int id = res.getDataNode().get(0).path("obj_id").asInt();

            Response res2 = InsertGeoPoint.run(client, id, x, y);
            logger.info(res2.getMessage());
        }  catch (Exception e) {
            logger.error("Unable to read the json file : " + filePath);
        } finally {
            client.closeSocket();
        }

    }


    private static JsonNode getGoodNode(JsonNode arrayNode, int index) {
        int i = 0;
        for (JsonNode node : arrayNode) {
            if (i == index) return node;
            i++;
        }
        return null;
    }

}
