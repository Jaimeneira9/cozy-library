package com.example.demo.model;

import com.example.demo.model.compositePK.AutorLibroId;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@IdClass(AutorLibroId.class)
public class AutorLibro {
    @Id
    @Column(name = "id_autor")
    private Long id_autor;

    @Id
    @Column(name = "id_libro")
    private Long id_libro;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "id_libro")
    private Libro libro;

}
