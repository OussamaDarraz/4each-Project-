package service.functionnal.map.methods;

import service.functionnal.map.objects.*;
import service.functionnal.map.tools.GeoPoint;
import service.network.Client;
import service.network.Request;
import service.network.Response;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetMapObject {
    public static List<MapObject> run(Client cl, int id) throws IOException {
        Request selectReq = new Request("MAP_GET_MAP_OBJECT");
        if(id > -1) selectReq.setParam("id", id);
        Response res = cl.call(selectReq);

        List<MapObject> obj = new ArrayList<>();
        JsonNode dataNode = res.getDataNode();
        for (JsonNode node : dataNode) {
            // récupération des infos du noeud courant
            int nodeId = node.path("id").asInt();
            String nodeLabel = node.path("label").asText();
            String nodeDesc = node.path("desc").asText();
            String nodeType = node.path("type").asText();
            int nodeX = node.path("x").asInt();
            int nodeY = node.path("y").asInt();
            int nodeOrder = node.path("order").asInt();
            GeoPoint nodeGeoPoint = new GeoPoint(nodeX, nodeY, nodeOrder);

            MapObject currentObj = null;
            for (MapObject ob : obj) {
                if(ob.getId() == nodeId) currentObj = ob;
            }
            if(currentObj != null) {
                if(nodeType.equals("FIELD") || nodeType.equals("BUILDING")) {
                    currentObj.addPoint(nodeGeoPoint);
                }
            } else {
                if(nodeType.equals("SENSOR")) {
                    Sensor s = new Sensor(nodeId, nodeLabel, nodeDesc);
                    s.setPosition(nodeGeoPoint);
                    obj.add(s);
                }
                if(nodeType.equals("FIELD")) {
                    Field f = new Field(nodeId, nodeLabel, nodeDesc);
                    f.addPoint(nodeGeoPoint);
                    obj.add(f);
                }
                if(nodeType.equals("BUILDING")) {
                    Building b = new Building(nodeId, nodeLabel, nodeDesc);
                    b.addPoint(nodeGeoPoint);
                    obj.add(b);
                }
                if(nodeType.equals("PANEL")) {
                    Panel b = new Panel(nodeId, nodeLabel, nodeDesc);
                    b.addPoint(nodeGeoPoint);
                    obj.add(b);
                }
            }
        }
        return obj;
    }
}
