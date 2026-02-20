package com.example.demo.model;

import com.example.demo.model.compositePK.AutorLibroId;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

@Table(name = "autor_libro")
public class AutorLibro {

    @EmbeddedId
    private AutorLibroId id;

    @ManyToOne
    @MapsId("idAutor")
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @ManyToOne
    @MapsId("idLibro")
    @JoinColumn(name = "id_libro")
    private Libro libro;


}
