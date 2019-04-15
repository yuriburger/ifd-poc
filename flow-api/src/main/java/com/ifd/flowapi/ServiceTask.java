package com.ifd.flowapi;

import org.flowable.engine.delegate.DelegateExecution;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceTask {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void execute(DelegateExecution execution, String reason) {
        // Send the correct message based on reason
        // Better: get all execution and task variables and put them in de message payload
        // Map<String, Object> processVariables = execution.getVariables();

        rabbitTemplate.convertAndSend(ApiApplication.topicExchangeName,
                "ifd.demo.hond",
                "{\"id\":" + execution.getVariable("id") +
                        ",\"name\":\"" + execution.getVariable("name") + "\"" +
                        ",\"reason\":\"" + reason + "\"}");
    }
}
