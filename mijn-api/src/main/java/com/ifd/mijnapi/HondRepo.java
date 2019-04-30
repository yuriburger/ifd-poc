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
public class HondRepo {

    @Value("${baseUrl}")
    private String baseUrl;

    @Autowired
    RestTemplate restTemplate;

    private static Honden listHonden = new Honden();

    private final RabbitTemplate rabbitTemplate;

    public HondRepo( RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    static {
        listHonden.getHondList().add(new Hond(1, "Fikkie", "NewImportComplete", "Import afgerond"));
        listHonden.getHondList().add(new Hond(2, "Woef", "NewImportComplete", "Import afgerond"));
        listHonden.getHondList().add(new Hond(3, "Bas", "NewImportComplete", "Import afgerond"));
    }

    public Honden getAllHonden() {
        return listHonden;
    }

    public Hond getHondById(Integer id) {
        return listHonden.getHondList()
                .stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void addHond(Hond hond) {
        listHonden.getHondList().add(hond);

        try {
            RestTemplate restTemplate = new RestTemplate();

            JSONObject body = new JSONObject();

            body.put("message", "NewImport");
            body.put("businessKey",hond.getId().toString());

            JSONArray variables = new JSONArray();

            variables.put(new JSONObject().put("name","id").put("value", hond.getId()));
            variables.put(new JSONObject().put("name","name").put("value", hond.getName()));
            variables.put(new JSONObject().put("name","reason").put("value", hond.getReason()));

            body.put("variables", variables);

            HttpEntity<String> entity = new HttpEntity<>(body.toString(), Util.createHeaders());
            restTemplate.exchange(this.baseUrl + "/runtime/process-instances" , HttpMethod.POST, entity, JSONObject.class);

            } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateHond(Hond hond, Integer id) {
        Hond hondToUpdate = listHonden.getHondList()
                .stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (hondToUpdate != null) {
            hond.setId(hondToUpdate.getId());
            listHonden.getHondList().set(listHonden.getHondList().indexOf(hondToUpdate), hond);
        }
    }

}
