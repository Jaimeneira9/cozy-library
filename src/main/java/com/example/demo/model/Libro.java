package com.example.demo.model;

import com.example.demo.model.compositePK.ListaLibro;
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
    private int anioPublicacion;

    @OneToMany(mappedBy = "libro")
    private List<AutorLibro> autores;

    @OneToMany(mappedBy = "libro")
    private List<Resenia> resenias;

    @OneToMany(mappedBy = "libro")
    private List<ListaLibro> listas;

}
