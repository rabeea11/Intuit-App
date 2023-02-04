package com.example.IntuitApp.kafka;

import com.example.IntuitApp.model.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Producer {

    @Value("${spring.kafka.topic.name}")
    private String orderTopic;

    private final KafkaTemplate<String,Payment> kafkaTemplate;

    public Producer(KafkaTemplate<String, Payment> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Payment payment) {
        kafkaTemplate.send(orderTopic, payment);

        log.info("Message Sent : {}", payment);
    }
}
