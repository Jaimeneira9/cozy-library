package com.example.demo.model;

import com.example.demo.model.compositePK.ListaLibroId;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "listaLibro")
public class ListaLibro {

    @EmbeddedId
    private ListaLibroId id;

    @ManyToOne
    @JoinColumn(name = "listalectura_id")
    private ListaLectura listaLectura;


    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    private LocalDate fechaAgregado;
    private Integer ordenEnLista;
}
