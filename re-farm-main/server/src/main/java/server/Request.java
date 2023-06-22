package server;

import business.anemo.domain.AnemometreValue;
import business.anemo.domain.ConfigurationAnemo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.DataInput;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class Request {
    public Connection co;
    private ObjectMapper mapper;
    private String process;
    private JsonNode params;
    private ArrayNode params1;

    public Request(String json, Connection co) throws JsonProcessingException {
        mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        this.process = root.path("process").asText();
        this.params = !root.path("params").isMissingNode() ? root.path("params") : null;
        this.co = co; // get the connection
    }

    public String getProcess() {

        return process;
    }

    public String getParam(String key) {
        if (params != null) {
            return params.path(key).asText();
        }
        return null;
    }

    public <T> T paramTo(Class<T> className) {
        try {
            return mapper.treeToValue(params, className);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public List<AnemometreValue> paramTo() {

        try {
            //System.out.println(params1.toString()+" :param1");

            return  mapper.treeToValue( params1, mapper.getTypeFactory().constructCollectionType(List.class, AnemometreValue.class));

           // mapper.treeToValue(params, (JavaType) anemometreValues);


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}

