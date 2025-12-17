package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
<<<<<<< HEAD
@Table(name = "resenias")
=======
@Table(name = "resenia")
>>>>>>> e9bb205ffd7bb5bcb564a6a3591819a20b1c6b3e
public class Resenia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double valoracion;
    private LocalDate fecha;
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_libro")
    Libro libro;

}
