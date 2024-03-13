package com.techvalens.shorturl.service;

import com.techvalens.shorturl.kafka.URLShortenerKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class URLShortenerService {

    private Map<String, String> urlMap = new HashMap<>();

    @Autowired
    private URLShortenerKafkaProducer kafkaProducer;

    @Value("${spring.kafka.topic.shortened-urls}")
    private String topic;

    public void shortenURL(String originalURL) {
        String shortenedURL = "http://localhost:8080/"+generateShortenedURL(originalURL);
        kafkaProducer.sendMessage(shortenedURL);
        urlMap.put(shortenedURL, originalURL);
    }

    public String getOriginalURL(String shortenedURL) {
        for (String key : urlMap.keySet()) {
            if (key.endsWith(shortenedURL)) {
                return urlMap.get(key);
            }
        }
        return null;
    }


    private String generateShortenedURL(String originalURL) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}

