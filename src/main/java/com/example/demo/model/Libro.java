package com.example.demo.model;

import com.example.demo.model.compositePK.ListaLibroId;
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
