package business.anemo;

import business.TemplateBusiness;
import business.anemo.domain.AnemometreValue;
import business.anemo.domain.ConfigurationAnemo;
import business.anemo.service.AnemoService;
import com.fasterxml.jackson.core.type.TypeReference;
import server.Request;
import server.Response;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AnemoController extends TemplateBusiness {
    private final AnemoService service;
    private Object AnemometreValue;

    public AnemoController(Connection connection) {
        this.service = new AnemoService(connection);
    }

    public Response fetchAllAnemoValueByLabel(Request request) {
        Response response = new Response("success");
        String label = request.getParam("object_label");
        List<AnemometreValue> anemometreValues = service.getAllAnemoValueByLabel(label);
        response.collectToArrayNode(anemometreValues);
        return response;
    }

    public Response fetchAllObjectLabel() {
        Response response = new Response("success");
        List<String> anemometreValues = service.getAllObjectLabel();
        response.collectToArrayNode(anemometreValues);
        return response;
    }


    public Response postOrPutAnemoConfig(Request req) {
        Response response = new Response("success");
        ConfigurationAnemo anemoConfig = req.paramTo(ConfigurationAnemo.class);
        service.configureAnemo(anemoConfig);
        response.collectToArrayNode(Collections.emptyList());
        return response;
    }

    public Response difference(Request req) {
        Response response = new Response("success");
        String label = req.getParam("object_label");
        response.differenceBeteween(service.differenceBetweenSpeed(label));
        return response;
    }
    public Response differenceGust(Request req) {
        Response response = new Response("success");
        String label = req.getParam("object_label");
        response.differenceBeteweenGust(service.differenceBetweenSpeedGust(label));
        return response;
    }
    public Response getNumberConfigured(Request req) {
        Response response = new Response("success");
        response.numberConfigured(service.getNumberAnemoConfigured());
        return response;
    }
    public Response getNumber(Request req) {
        Response response = new Response("success");
        response.numberAllAnemo(service.getNumberAnemo());
        return response;
    }
    public Response fetchAllConfiguration(Request req){
        Response response = new Response("success");
        response.collectToArrayNode(service.getAllConfiguration());
        return response;
    }
    public Response insertConfig(Request req){
        Response response = new Response("success");
        ConfigurationAnemo configurationAnemo = req.paramTo(ConfigurationAnemo.class);
        service.configureAnemo(configurationAnemo);
        response.collectToArrayNode(Collections.emptyList());
        return response;
    }
    public Response insertvalue(){
        Response response = new Response("success");
        service.insertValues();
        response.setMessage("sucess");
        return response;
    }

}
