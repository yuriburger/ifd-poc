package com.ifd.flowapi;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.io.IOException;

@Component
public class Receiver {
    // Alternatief is REST, nu even voor het gemak via de Java API
    protected final RuntimeService runtimeService;

    public Receiver(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public void receiveMessage(String message) {
        System.out.println("[X] MessageReceiver: message payload: " + message);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode inputObject = objectMapper.readTree(message);

            String reason = inputObject.get("reason").asText();

            if (reason.equals("NieuweImportHond")) {

                Map<String, Object> processVariables = new HashMap<>();

                Iterator<Map.Entry<String, JsonNode>> nodes = inputObject.fields();

                while (nodes.hasNext()) {
                    Map.Entry<String, JsonNode> entry = nodes.next();
                    processVariables.put(entry.getKey(),entry.getValue().asText());
                }

                ProcessInstance processInstance = runtimeService.createProcessInstanceBuilder()
                        .messageName("ImportNieuweHond")
                        .variables(processVariables)
                        .start();

                // Alternatief: Post message to Flow-Api
                // {
                //   "message":"ImportNieuweHond",
                //   "businessKey":"12001",
                //   "variables": [
                //      {
                //        "name":"myVar1",
                //        "value":"This is variable 1"
                //      }
                //   ]
                //}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
