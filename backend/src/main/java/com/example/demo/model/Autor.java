package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "autores")

public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor")
    private Long id;
    @Column(name = "nombre")
    private String nombre;


    @OneToMany(mappedBy ="autor")
    private List<AutorLibro> libros;
    public Autor() {}
    // Constructor que acepta nombre
    public Autor(String nombre) {
        this.nombre = nombre;
    }

}
