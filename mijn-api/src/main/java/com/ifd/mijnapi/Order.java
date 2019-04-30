package com.ifd.mijnapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Order {

    public Order() {}

    public Order(Integer id, Integer numberOfItems, String reason, String status) {
        super();
        this.id = id;
        this.numberOfItems = numberOfItems;
        this.reason = reason;
        this.status = status;
    }

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer numberOfItems;

    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Order [id=" + id +
                "# items=" + numberOfItems +
                "status=" + status +
                "reason=" + reason +"]";
    }

    public String toJSON() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(this);
        return json;
    }
}
