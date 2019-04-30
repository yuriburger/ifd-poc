package com.ifd.mijnapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/lab")
public class LabController {

    @Value("${baseUrl}")
    private String baseUrl;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private OrderRepo orderRepo;

    @RequestMapping(value="", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getOrders()
    {
        RestTemplate restTemplate = new RestTemplate();

        JSONObject body = new JSONObject();
        JSONArray variables = new JSONArray();

        variables.put(new JSONObject()
                .put("name","status")
                .put("value", "Opdracht in behandeling")
                .put("operation", "equals")
                .put( "type", "string")
        );

        body.put("processInstanceVariables", variables);

        HttpEntity<String> entity = new HttpEntity<>(body.toString(), Util.createHeaders());
        ResponseEntity<String> result = restTemplate.exchange(this.baseUrl + "/query/tasks?candidateGroup=Medewerker Lab&includeProcessVariables=true", HttpMethod.POST, entity, String.class);

        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> completeTask(@PathVariable("id") String id, @RequestBody String body) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity = new HttpEntity<>(body, Util.createHeaders());
        ResponseEntity<String> result = restTemplate.exchange(String.format("%s/runtime/tasks/%s", this.baseUrl, id), HttpMethod.POST, entity, String.class);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/prepare", method = RequestMethod.PUT)
    public ResponseEntity<?> prepareOrder() {
        RestTemplate restTemplate = new RestTemplate();

        JSONObject body = new JSONObject();
        body.put("processDefinitionKey", "dna-afstamming-1");
        JSONArray variables = new JSONArray();

        variables.put(new JSONObject()
                .put("name","status")
                .put("value", "Opdracht klaargezet")
                .put("operation", "equals")
                .put( "type", "string")
        );

        body.put("variables", variables);

        HttpEntity<String> entity = new HttpEntity<>(body.toString(), Util.createHeaders());
        ResponseEntity<String> result = restTemplate.exchange(this.baseUrl + "/query/process-instances", HttpMethod.POST, entity, String.class);

        try {

            final JsonNode arrNode = new ObjectMapper().readTree(result.getBody()).get("data");

            if (arrNode.isArray()) {
                for (final JsonNode objNode : arrNode) {
                    JSONObject bodyItem = new JSONObject();
                    bodyItem
                            .put("name","status")
                            .put("value", "Opdracht in behandeling")
                            .put( "type", "string");

                    HttpEntity<String> entityItem = new HttpEntity<>(bodyItem.toString(), Util.createHeaders());
                    ResponseEntity<String> resultItem = restTemplate.exchange(this.baseUrl + "/runtime/process-instances/" + objNode.get("id").asText() + "/variables/status", HttpMethod.PUT, entityItem, String.class);

                    Order order = orderRepo.getOrderById(objNode.get("businessKey").asInt());

                    if (order != null) {
                        order.setStatus("Opdracht in behandeling");
                        orderRepo.updateOrder(order, objNode.get("businessKey").asInt());
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
