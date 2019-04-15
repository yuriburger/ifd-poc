package com.ifd.mijnapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Repository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Repository
public class HondRepo {
    private static Honden listHonden = new Honden();

    private final RabbitTemplate rabbitTemplate;

    public HondRepo( RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    static {
        listHonden.getHondList().add(new Hond(1, "Fikkie", "NieuweImportHondComplete"));
        listHonden.getHondList().add(new Hond(2, "Woef", "NieuweImportHondComplete"));
        listHonden.getHondList().add(new Hond(3, "Bas", "NieuweImportHondComplete"));
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

        // Send the correct message based on reason
        try {
            rabbitTemplate.convertAndSend(IfdApplication.topicExchangeName, "ifd.demo.import", hond.toJSON());
        } catch (JsonProcessingException e) {
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
