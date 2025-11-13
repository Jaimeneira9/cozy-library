package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_libro")
    private long id;
    private String titulo;
    private String urlPortada;
    private int anyoPublicacion;

    @OneToMany(mappedBy = "libro")
    private List<AutorLibro> autores;

}
