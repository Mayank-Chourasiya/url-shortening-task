package com.techvalens.shorturl.controller;

import com.techvalens.shorturl.service.URLShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
public class URLShortenerController {

    @Autowired
    private URLShortenerService urlShortenerService;

    @PostMapping("/url")
    public void shortenURL(@RequestBody String originalURL) {

        System.out.println(originalURL);
        CompletableFuture.runAsync(() -> urlShortenerService.shortenURL(originalURL));
    }

    @GetMapping("/{shortenedUrl}")
    public String getOriginalURL(@PathVariable String shortenedUrl) {
        return urlShortenerService.getOriginalURL(shortenedUrl);
    }
}
