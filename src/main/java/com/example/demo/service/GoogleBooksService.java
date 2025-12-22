package com.example.demo.service;

import com.example.demo.modelDTO.GoogleBooksResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleBooksService {
    private final RestTemplate restTemplate;

    public GoogleBooksService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public GoogleBooksResponseDTO buscarLibros(String query) {
        String url = "https://www.googleapis.com/books/v1/volumes?q=" + query;
        return restTemplate.getForObject(url, GoogleBooksResponseDTO.class);
    }
}
