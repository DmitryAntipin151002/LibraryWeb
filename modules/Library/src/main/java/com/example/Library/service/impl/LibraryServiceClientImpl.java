package com.example.Library.service.impl;

import com.example.Library.service.LibraryServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LibraryServiceClientImpl implements LibraryServiceClient {
    private final RestTemplate restTemplate;

    @Autowired
    public LibraryServiceClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

@Override
    public void notifyLibraryService(Integer bookId) {
        String url = "http://localhost:9940/library/addBook?bookId=" + bookId;
        restTemplate.postForEntity(url, null, Void.class);
    }
}

