package service.functionnal.pann.repository;

import service.functionnal.pann.domain.PositionChangedValues;
import service.functionnal.pann.domain.PositionValues;
import service.functionnal.pann.domain.SolarPanelValue;
import service.network.Client;
import service.network.Request;
import service.network.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SolarRepository {
    private final static Client CLIENT = new Client();
    private final static ObjectMapper MAPPER = new ObjectMapper();
    private final static Logger LOGGER = LoggerFactory.getLogger(SolarRepository.class);

    public List<SolarPanelValue> findObjectById(int id) {
        List<SolarPanelValue> result = new ArrayList<>();
        Request request = new Request("READ_SOLAR_ID");
        request.setParam("id_sol_panel", id);
        try {
            Response res = CLIENT.call(request);
            JsonNode data = res.getDataNode();
            LOGGER.info("FindObjectByID = {}", data);
            for (JsonNode ad : data) {
                result.add(MAPPER.treeToValue(ad, SolarPanelValue.class));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;

    }

    public List<String> findAllObjectByLabel() {
        List<String> result = new ArrayList<>();
        Request request = new Request("READ_All_SOLAR_PLACED");
        try {
            Response res = CLIENT.call(request);
            JsonNode data = res.getDataNode();
            LOGGER.info("findAllObjectByLabel = {}", data);
            result.addAll(MAPPER.treeToValue(data, List.class));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Double> getEnergyValue() {
        List<Double> result = new ArrayList<>();
        Request request = new Request("READ_ALL_ENERGY_PANEL");
        try {
            Response res = CLIENT.call(request);
            JsonNode data = res.getDataNode();
            LOGGER.info("findAllEnergyValues = {}", data);
            result.addAll(MAPPER.treeToValue(data, List.class));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;

    }

    public List<Double> totalEnergyByType(String type) {
        List<Double> result = new ArrayList<>();
        Request request = new Request("READ_ENERGY_PANEL_TYPE");
        request.setParam("panel_type", type);
        try {
            Response res = CLIENT.call(request);
            JsonNode data = res.getDataNode();
            LOGGER.info("FindAllEnergyByType = {}", data);
            result.addAll(MAPPER.treeToValue(data, List.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;

    }

    public List<Integer> findIdByType(String type) {
        List<Integer> result = new ArrayList<>();
        Request request = new Request("READ_ID_PANEL_TYPE");
        request.setParam("panel_type", type);
        try {
            Response res = CLIENT.call(request);
            JsonNode data = res.getDataNode();
            LOGGER.info("FindAllIdByType = {}", data);
            result.addAll(MAPPER.treeToValue(data, List.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;

    }

    public List<PositionValues> findPlaces() {
        List<PositionValues> result = new ArrayList<>();
        Request request = new Request("READ_PlACES");

        try {
            Response res = CLIENT.call(request);
            JsonNode data = res.getDataNode();
            LOGGER.info("FindPlaces = {}", data);
            for (JsonNode ad : data) {
                result.add(MAPPER.treeToValue(ad, PositionValues.class));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void insertanobject(String obj_label) {
        Request request = new Request("INSERT_OBJECT");
        request.parsePayload(obj_label);
        try {
            Response res = CLIENT.call(request);
            JsonNode data = res.getDataNode();
            LOGGER.info("insert an object = {}", data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateapanel(String panel_type) {
        Request request = new Request("UPDATE_PANEL");
        request.parsePayload(panel_type);
        try {
            Response res = CLIENT.call(request);
            JsonNode data = res.getDataNode();
            LOGGER.info("update a panel = {}", data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void configtheplace(int sunshine_lvl) {
        Request request = new Request("CONFIG_A_PLACE");
        request.parsePayload(sunshine_lvl);
        try {
            Response res = CLIENT.call(request);
            JsonNode data = res.getDataNode();
            LOGGER.info("config a place = {}", data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PositionChangedValues> findPlacesWEnergy() {
        List<PositionChangedValues> result = new ArrayList<>();
        Request request = new Request("READ_PlACES_WENERGY");

        try {
            Response res = CLIENT.call(request);
            JsonNode data = res.getDataNode();
            LOGGER.info("FindPlacesWENERGY = {}", data);
            for (JsonNode ad : data) {
                result.add(MAPPER.treeToValue(ad, PositionChangedValues.class));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public List<Double> findAllEnergy() {
        List<Double> result = new ArrayList<>();
        Request request = new Request("READ_Energy");
        try {
            Response res = CLIENT.call(request);
            JsonNode data = res.getDataNode();
            LOGGER.info("findAllEnergy = {}", data);
            result.addAll(MAPPER.treeToValue(data, List.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;

    }
    public List<PositionChangedValues> findCalc(double energy) {
        List<PositionChangedValues> result = new ArrayList<>();
        Request request = new Request("CALC_ENERGY");
        request.setParam("energy", energy);
        try {
            Response res = CLIENT.call(request);
            JsonNode data = res.getDataNode();
            LOGGER.info("CALCENERGY = {}", data);
            for (JsonNode ad : data) {
                result.add(MAPPER.treeToValue(ad, PositionChangedValues.class));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;

    }

    public void createtoMap() {
        Request request = new Request("CREATE_PAN");
        try {
            Response res = CLIENT.call(request);
            JsonNode data = res.getDataNode();
            LOGGER.info("create a panel = {}", data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public int findAPanel() {
        Request request = new Request("SEARCH_PAN");
        try {
            Response res = CLIENT.call(request);
            JsonNode data = res.getDataNode();
            LOGGER.info(" Pan_id = {}", data);
            return Integer.parseInt(data.get(0).get("Id_pan").toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertaPanel(int id , int x , int y) {
        Request request = new Request("PANEL_MAP");
        request.setParam("id", id);
        request.setParam("x", x);
        request.setParam("y", y);

        try {
            Response res = CLIENT.call(request);
            JsonNode data = res.getDataNode();
            LOGGER.info("panel add = {}", data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public int totalPanel() {
        Request request = new Request("TOT_PAN");
        try {
            Response res = CLIENT.call(request);
            JsonNode data = res.getDataNode();
            LOGGER.info(" Pan_total = {}", data);
            return Integer.parseInt(data.get(0).get("totpan").toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

