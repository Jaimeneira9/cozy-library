package com.example.demo.modelDTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UsuarioRequestDTO {
    private String nombre;

    private LocalDateTime fechaRegistro;
    private String email;
    private String pathImagenPerfil;

    private int valoraciones;
}

