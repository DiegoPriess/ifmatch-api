package com.ifmatch.ifmatchservice.kafkaCommon;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Autowired
    KafkaProperties properties;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                properties.buildConsumerProperties());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, String> listener =
                new ConcurrentKafkaListenerContainerFactory<>();

        listener.setConsumerFactory(consumerFactory());

        listener.getContainerProperties()
                .setMissingTopicsFatal(false);

        listener.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);

        listener.getContainerProperties().setSyncCommits(Boolean.TRUE);

        return listener;
    }
}