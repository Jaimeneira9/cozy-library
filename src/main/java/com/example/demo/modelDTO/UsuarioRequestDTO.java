package com.example.demo.modelDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioRequestDTO {
    private String nombre;
    private int valoraciones;
    private String fotoPerfil;

}
