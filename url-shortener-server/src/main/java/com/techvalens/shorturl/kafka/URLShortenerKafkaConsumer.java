package com.techvalens.shorturl.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class URLShortenerKafkaConsumer {

    @KafkaListener(topics = "${spring.kafka.topic.shortened-urls}", groupId = "url-shortener-group")
    public void listen(String message) {
        log.info("Received message from Kafka: {}",message);
    }
}

