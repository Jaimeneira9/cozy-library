package com.example.demo.model;

import com.example.demo.model.compositePK.ListaLibroId;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "lista_libro")
public class ListaLibro implements Serializable {

    @EmbeddedId
    private ListaLibroId id;

    @ManyToOne
    @MapsId("listaId")
    @JoinColumn(name = "lista_id")
    private ListaLectura listaLectura;

    @ManyToOne
    @MapsId("libroId")
    @JoinColumn(name = "libro_id")
    private Libro libro;

    @Column(name = "fecha_agregado")
    private LocalDate fechaAgregado;

    @Column(name = "orden_en_lista")
    private Integer ordenEnLista;
}
