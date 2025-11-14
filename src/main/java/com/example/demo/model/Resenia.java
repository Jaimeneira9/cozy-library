package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Resenia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
