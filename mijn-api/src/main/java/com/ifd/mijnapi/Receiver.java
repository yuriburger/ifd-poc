package com.ifd.mijnapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Receiver {
    @Autowired
    private HondRepo hondRepo;

    public Receiver() {}

    public void receiveMessage(String message) {
        System.out.println("[X] MessageReceiver: message payload: " + message);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode inputObject = objectMapper.readTree(message);

            String reason = inputObject.get("reason").asText();

            if (reason.equals("NieuweImportHondComplete")) {
                Hond hond = hondRepo.getHondById(inputObject.get("id").asInt());

                if (hond != null) {
                    hond.setReason("NieuweImportHondComplete");
                    hondRepo.updateHond(hond,inputObject.get("id").asInt());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
