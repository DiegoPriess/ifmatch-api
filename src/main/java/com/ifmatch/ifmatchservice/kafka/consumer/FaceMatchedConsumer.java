package com.ifmatch.ifmatchservice.kafka.consumer;

import com.ifmatch.ifmatchservice.enums.UserStatus;
import com.ifmatch.ifmatchservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class FaceMatchedConsumer {

    @Autowired
    public FaceMatchedConsumer(UserService service) {
        this.service = service;
    }

    final UserService service;
    private static final String topic = "FACE_MATCHED";

    @KafkaListener(topics = topic)
    private void changeUserStatus(@Payload String id, Acknowledgment ack) {
        service.changeStatus(Long.parseLong(id), UserStatus.AGUARDANDO_ATENDIMENTO);
        ack.acknowledge();
    }
}