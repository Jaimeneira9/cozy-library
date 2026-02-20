package com.example.demo.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "libros")

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private long id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "url_portada")
    private String urlPortada;
    @Column(name = "anip_publicacion")
    private String anioPublicacion;
    @Column(name = "num_paginas")
    private int numPaginas;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "google_id")
    private String googleId;
    @OneToMany(mappedBy = "libro")
    private List<AutorLibro> autores;

    @OneToMany(mappedBy = "libro")
    private List<Resenia> resenias;

    @OneToMany(mappedBy = "libro")
    private List<ListaLibro> listas;

}
