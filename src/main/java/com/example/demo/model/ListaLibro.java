package com.example.demo.model;

import com.example.demo.model.compositePK.ListaLibroId;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

import java.io.Serializable;

@Data
@Entity
@Table(name = "lista_libro")
public class ListaLibro implements Serializable {

    @EmbeddedId
    private ListaLibroId id;

    @ManyToOne
    @MapsId("id_lista")
    @JoinColumn(name = "id_lista")
    private ListaLectura listaLectura;

    @ManyToOne
    @MapsId("id_libro")
    @JoinColumn(name = "id_libro")
    private Libro libro;
}