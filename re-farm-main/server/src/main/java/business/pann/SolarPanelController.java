package business.pann;

import business.TemplateBusiness;
import business.anemo.domain.ConfigurationAnemo;
import business.pann.domain.PositionChangedValues;
import business.pann.domain.PositionValues;
import business.pann.domain.SolarPanelValue;
import business.pann.service.SolarService;
import server.Request;
import server.Response;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

public class SolarPanelController extends TemplateBusiness {

    private final SolarService service;

    public SolarPanelController(Connection connection) {
        this.service = new SolarService(connection);
    }

    public Response fetchAllSolarPanelById (Request request)
    {
        Response response = new Response("succes");
        int id = Integer.parseInt(request.getParam("id_sol_panel"));
        List<SolarPanelValue> solarPanelValues = service.getAllSolarPanelById(id);
        response.collectToArrayNode(solarPanelValues);
        return response ;
    }
    public Response fetchAllPlacedSolarPanelByLabel() {
        Response response = new Response("success");
        List<String> SolarPanelValues = service.getAllSolarPanelByLabel();
        response.collectToArrayNode(SolarPanelValues);
        return response;
    }
    public Response fetchAllEnergyValues() {
        Response response = new Response("success");
        List<Double> solarPanelEnergy = service.getEnergieValue();
        response.collectToArrayNode(solarPanelEnergy);
        return response;
    }

    public Response fetchAllSolarByType(Request request) {
        Response response = new Response("success");
        String type = request.getParam("panel_type");
        List<SolarPanelValue> solarPanelType = service.getAllSolarPanelByType(type);
        response.collectToArrayNode(solarPanelType);
        return response;
    }
    public Response fetchAllEnergieByType ( Request request)
    {
        Response response = new Response("success");
        String type = request.getParam("panel_type");
        List<Double> solarEnergie = service.totalEnergyByType(type);
        response.collectToArrayNode(solarEnergie);
        return response;
    }
    public Response getAllIdByType ( Request request)
    {
        Response response = new Response("success");
        String type = request.getParam("panel_type");
        List<Integer> solarId = service.getAllIdByType(type);
        response.collectToArrayNode(solarId);
        return response;
    }
    public Response getAllPlaces ( )
    {
        Response response = new Response("success");
        List<PositionValues> positionValues = service.getAllPlaces();
        response.collectToArrayNode(positionValues);
        return response ;
    }
    public Response insertObject (Request req) {
        Response response = new Response("success");
        String label = req.getParam("obj_label");
        service.insertintoObject(label);
        response.collectToArrayNode(Collections.emptyList());
        return response;
    }
    public Response updateapanel(Request req) {
        Response response = new Response("success");
        String type = req.getParam("panel_type");
        service.updateapanel(type);
        response.collectToArrayNode(Collections.emptyList());
        return response;
    }
    public Response configTheplace(Request req) {
        Response response = new Response("success");
        int sunshine = Integer.parseInt(req.getParam("sunshine_lvl"));
        service.configtheplace(sunshine);
        response.collectToArrayNode(Collections.emptyList());
        return response;
    }
    public Response getAllPlacesWenergie ( )
    {
        Response response = new Response("success");
        List<PositionChangedValues> positionValues = service.getAllPlacesWenergie();
        response.collectToArrayNode(positionValues);
        return response ;
    }

    public Response getAllEnergie ()
    {
        Response response = new Response("success");
        List<Double> energyValues = service.FindAllEnergy();
        response.collectToArrayNode(energyValues);
        return response ;

    }
    public Response fetchAllWithEnergy ( Request request)
    {
        Response response = new Response("success");
        double energy = Double.parseDouble(request.getParam("energy"));
        List<PositionChangedValues> changedvalues = service.findTotalByEnergie(energy);
        response.collectToArrayNode(changedvalues);
        return response;
    }
    public Response createtoMap () {
        Response response = new Response("success");
        service.createaPanel();
        response.collectToArrayNode(Collections.emptyList());
        return response;
    }
    public Response serchaPanel () {
        Response response = new Response("success");
        response.idAllpanel(service.findaPanel());
        return response;
    }

    public Response paneltoaMap (Request req) {
        Response response = new Response("success");
        Integer id = Integer.parseInt(req.getParam("id"));
        Integer x = Integer.parseInt(req.getParam("x"));
        Integer y = Integer.parseInt(req.getParam("y"));
        service.paneltoMap(id,x,y);
        response.collectToArrayNode(Collections.emptyList());
        return response;

    }
    public Response totalPanel () {
        Response response = new Response("success");
        response.totpanel(service.totalPanel());
        return response;
    }
}

