package service.functionnal.anemo.repository;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.json.JSONObject;
import service.functionnal.anemo.domain.AnemometreValue;
import service.functionnal.anemo.domain.ConfigurationAnemo;
import service.network.Client;
import service.network.Request;
import service.network.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnemoRepository {
    private final static Client CLIENT = new Client();
    private final static ObjectMapper MAPPER = new ObjectMapper();
    private final static Logger LOGGER = LoggerFactory.getLogger(AnemoRepository.class);
    private final static JSONObject jSonObject= new JSONObject();

    public List<AnemometreValue> findByObjectLabel(String label) {
        List<AnemometreValue> result = new ArrayList<>();
        Request request = new Request("READ_ANEMOVALUE_BY_LABEL");
        request.setParam("object_label", label );
        try {
            Response res = CLIENT.call(request);
            JsonNode data = res.getDataNode();
            LOGGER.info("findByObjectLabel = {}", data);
            for(JsonNode el: data) {
                result.add(MAPPER.treeToValue(el, AnemometreValue.class));
            }

        } catch (IOException e) {
             //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return result;
    }

    public List<String> findAllObjectLabel() {
        List<String> result = new ArrayList<>();
        Request request = new Request("READ_ALL_LABEL");
        try {
            Response res = CLIENT.call(request);
            JsonNode data = res.getDataNode();
            LOGGER.info("findAllObjectLabel = {}", data);
            result.addAll(MAPPER.treeToValue(data, List.class));

        } catch (IOException e) {
           // throw new RuntimeException(e);
            e.printStackTrace();
        }
        return result;
    }
    public void saveOrUpdateAnemoConfig(ConfigurationAnemo configurationAnemo) {
        Request request = new Request("CREATE_UPDATE_CONFIG_ANEMO");
        request.parsePayload(configurationAnemo);
        try {
            Response res = CLIENT.call(request);
            JsonNode data = res.getDataNode();
           // LOGGER.info("saveOrUpdateAnemoConfig = {}", data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public int difference(String label) {
        Request request = new Request("DIFF_CONFIG");
            request.setParam("object_label", label );
            try {
                Response res = CLIENT.call(request);
                JsonNode data = res.getDataNode();
                LOGGER.info("dif = {}", data);
                return Integer.parseInt(data.get(0).get("Dif").toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }
    public int differenceGust(String label) {
        Request request = new Request("DIFF_GUST");
        request.setParam("object_label", label );
        try {
            Response res = CLIENT.call(request);
            JsonNode data = res.getDataNode();
            LOGGER.info("difGust = {}", data);
            return Integer.parseInt(data.get(0).get("DifGust").toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public int numberConfigured() {
        Request request = new Request("NUMB_ANEMO_CONFIG");
        try {
            Response res = CLIENT.call(request);
            JsonNode data = res.getDataNode();
            LOGGER.info(" number_anemo_configured= {}", data);
            return Integer.parseInt(data.get(0).get("Number_config").toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public int numberNoConfigured() {
        AnemoRepository anemoRepository = new AnemoRepository();
        Request request = new Request("NUMB_ANEMO");
        try {
            Response res = CLIENT.call(request);
            JsonNode data = res.getDataNode();
            LOGGER.info("number_anemo_no_configured = {}", data);
            return Integer.parseInt(data.get(0).get("Number_all").toString())-(anemoRepository.numberConfigured());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public List<ConfigurationAnemo> fetchAllAnemoConfigured() {
        List<ConfigurationAnemo> result = new ArrayList<>();
        Request request = new Request("SELECT_CONFIG");
        try {
            Response res = CLIENT.call(request);
            JsonNode data = res.getDataNode();
            //LOGGER.info("anemo_configuration= {}", data);
            for(JsonNode el: data) {
                result.add(MAPPER.treeToValue(el, ConfigurationAnemo.class));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
    public void insertValues() {

        Request request = new Request("INSERT_VALUE");
        try {
            //Response res =
            CLIENT.call(request);
            //JsonNode data = res.getDataNode();
            //LOGGER.info("anemo_Values are inserted = {}",data);



            }
         catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
