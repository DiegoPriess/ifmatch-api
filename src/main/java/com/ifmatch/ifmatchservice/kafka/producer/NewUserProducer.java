package com.ifmatch.ifmatchservice.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NewUserProducer {
    private static final String TOPIC = "NEW_USER";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendEvent() {
        kafkaTemplate.send(TOPIC, null);
    }
}
