package com.example.demo.modelDTO;

import com.example.demo.model.AutorLibro;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LibroRequestDTO {
    private String titulo;
    private String urlPortada;
    private int anyoPublicacion;
    private List<AutorLibro> autores;
}
