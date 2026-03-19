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
    private String url_imagen_perfil;

    private int valoraciones;
}

