package com.example.demo.modelDTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
@Builder
public class ReseniaRequestDTO {
    private Long idLibro;
    private Long idUsuario;

    // Información para mostrar
    private String tituloLibro;
    private List<String> nombresAutores;
    private String urlPortada; // Para el diseño visual de Figma
    private String nombreUsuario;
    private LocalDate fecha;
    private double valoracion;
    private String comentario;
}
