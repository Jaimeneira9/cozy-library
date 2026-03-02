package com.example.demo.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private long idUsuario;
    private String nombre;
}
