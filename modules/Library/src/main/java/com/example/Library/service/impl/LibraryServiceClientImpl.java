package com.example.Library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LibraryServiceClientImpl {
    private final RestTemplate restTemplate;

    @Autowired
    public LibraryServiceClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void notifyLibraryService(Integer bookId) {
        String url = "http://localhost:9940/library/addBook?bookId=" + bookId;
        restTemplate.postForEntity(url, null, Void.class);
    }
}

