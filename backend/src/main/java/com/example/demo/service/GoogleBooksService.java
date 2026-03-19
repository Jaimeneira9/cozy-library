package com.example.demo.service;

import com.example.demo.modelDTO.InfoLibroDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class GoogleBooksService {
    private final RestTemplate restTemplate;
    @Value("${python.service.url}")
    private String pythonServiceUrl;
    public GoogleBooksService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public List<InfoLibroDTO> buscarLibrosEnPython(String query) {
        String url = pythonServiceUrl + "/api/v1/libros/buscar?q=" + query;

        InfoLibroDTO[] response = restTemplate.getForObject(url, InfoLibroDTO[].class);
        return Arrays.asList(response);
    }
}
