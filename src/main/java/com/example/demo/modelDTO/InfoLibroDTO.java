package com.example.demo.modelDTO;

import lombok.Data;

import java.util.List;

@Data
public class InfoLibroDTO {
    private String titulo;
    private List<String> autores;
    private String anio;
    private String portada;
    private boolean isStored;
}
