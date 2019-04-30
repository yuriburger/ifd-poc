package com.ifd.mijnapi;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class OrderRepo {

    @Value("${baseUrl}")
    private String baseUrl;

    @Autowired
    RestTemplate restTemplate;

    private static Orders listOrders = new Orders();

    private final RabbitTemplate rabbitTemplate;

    public OrderRepo( RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    static {
        listOrders.getOrderList().add(new Order(1, 4, "OrderComplete", "Opdracht afgerond"));
        listOrders.getOrderList().add(new Order(2, 6, "OrderComplete", "Opdracht afgerond"));
    }

    public Orders getAllOrders() {
        return listOrders;
    }

    public Order getOrderById(Integer id) {
        return listOrders.getOrderList()
                .stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void addOrder(Order order) {
        listOrders.getOrderList().add(order);

        try {
            RestTemplate restTemplate = new RestTemplate();

            JSONObject body = new JSONObject();
            JSONArray variables = new JSONArray();

            body.put("message", "NewOrder");
            body.put("businessKey",order.getId());
            variables.put(new JSONObject().put("name","status").put("value", order.getStatus()));
            variables.put(new JSONObject().put("name","orderId").put("value", order.getId()));

            if (order.getNumberOfItems() > 0) {
                JSONArray samplesList = new JSONArray();

                for (int x = 1; x <= order.getNumberOfItems(); x++) {
                    samplesList.put(x);
                }
                variables.put(new JSONObject().put("name","samplesList").put("value", samplesList));
            }

            body.put("variables", variables);

            HttpEntity<String> entity = new HttpEntity<>(body.toString(), Util.createHeaders());
            restTemplate.exchange(this.baseUrl + "/runtime/process-instances" , HttpMethod.POST, entity, JSONObject.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateOrder(Order order, Integer id) {
        Order orderToUpdate = listOrders.getOrderList()
                .stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (orderToUpdate != null) {
            order.setId(orderToUpdate.getId());
            listOrders.getOrderList().set(listOrders.getOrderList().indexOf(orderToUpdate), order);
        }
    }
}
