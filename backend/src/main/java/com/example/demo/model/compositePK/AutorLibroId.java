package com.example.demo.model.compositePK;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.Data;


import java.io.Serializable;

@Embeddable
@Data
public class AutorLibroId implements Serializable {
    @Column(name = "id_autor")
    private Long idAutor;

    @Column(name = "id_libro")
    private Long idLibro;
}
