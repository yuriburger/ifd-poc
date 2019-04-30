package com.ifd.mijnapi;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/orders")
public class OrderController {

    @Autowired
    private OrderRepo orderRepo;

    @GetMapping(path="", produces = "application/json")
    public ResponseEntity<Object> getOrders()
    {
        List<JSONObject> entities = new ArrayList<>();
        for (Order order : orderRepo.getAllOrders().getOrderList()) {
            entities.add(new JSONObject(order));
        }

        return new ResponseEntity<>(entities.toString(), HttpStatus.OK);
    }

    @PostMapping(path= "", consumes = "application/json")
    public ResponseEntity<Object> addOrder(@RequestBody Order order)
    {
        Integer id = orderRepo.getAllOrders().getOrderList().size() + 1;
        order.setReason("NewOrderStart");
        order.setStatus("Opdracht klaargezet");
        order.setId(id);

        orderRepo.addOrder(order);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
