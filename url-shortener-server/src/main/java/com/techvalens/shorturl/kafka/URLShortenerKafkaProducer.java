package com.techvalens.shorturl.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class URLShortenerKafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic.shortened-urls}")
    private String topic;

    public void sendMessage(String message) {
        kafkaTemplate.send(topic, message);
    }
}

