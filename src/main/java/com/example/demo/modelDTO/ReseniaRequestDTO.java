package com.example.demo.modelDTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class ReseniaRequestDTO {
    private String tituloLibro;
    private String nombreAutor;
    private String nombreUsuario;
    private LocalDate fecha;
    private double valoracion;
    private String comentario;
}
