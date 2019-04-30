package com.ifd.mijnapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/messages")
public class MessageController {
    @Autowired
    private HondRepo hondRepo;

    @Autowired
    private OrderRepo orderRepo;

    @PostMapping(path= "", consumes = "application/json")
    public ResponseEntity<Object> addMessage(@RequestBody String message)
    {
        System.out.println("[X] MessageReceiver: message payload: " + message);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode inputObject = objectMapper.readTree(message);

            String reason = inputObject.get("reason").asText();

            if (reason.equals("NewImportComplete")) {
                Hond hond = hondRepo.getHondById(inputObject.get("id").asInt());

                if (hond != null) {
                    hond.setReason("NewImportComplete");
                    hond.setStatus("Import afgerond");
                    hondRepo.updateHond(hond,inputObject.get("id").asInt());
                }
            }

            if (reason.equals("NewOrderComplete")) {
                Order order = orderRepo.getOrderById(inputObject.get("id").asInt());

                if (order != null) {
                    order.setReason("NewOrderComplete");
                    order.setStatus("Opdracht afgerond");
                    orderRepo.updateOrder(order,inputObject.get("id").asInt());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
