package com.example.demo.model.compositePK;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ListaLibroId implements Serializable {
    private Long id_lista;
    private Long id_libro;
}
