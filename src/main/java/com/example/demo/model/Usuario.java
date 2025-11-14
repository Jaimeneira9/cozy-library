package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nombre;
    private String contraseña;
    private LocalDateTime fechaRegistro;
    private String email;
    private String pathImagenPerfil;

    @OneToMany(mappedBy = "usuario")
    List<Resenia> resenias;
}
