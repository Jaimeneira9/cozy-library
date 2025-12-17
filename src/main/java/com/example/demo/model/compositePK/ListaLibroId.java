package com.example.demo.model.compositePK;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class ListaLibroId implements Serializable {
    private Long id_libro;
    private Long id_lista;
=======
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ListaLibroId implements Serializable {
    private Long id_lista;
    private Long id_libro;
>>>>>>> e9bb205ffd7bb5bcb564a6a3591819a20b1c6b3e
}
