package com.example.demo.modelDTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UsuarioRequestDTO {
    private String nombre;
<<<<<<< HEAD
    private LocalDateTime fechaRegistro;
    private String email;
    private String pathImagenPerfil;
=======
    private int valoraciones;
    private String fotoPerfil;

>>>>>>> e9bb205ffd7bb5bcb564a6a3591819a20b1c6b3e
}
