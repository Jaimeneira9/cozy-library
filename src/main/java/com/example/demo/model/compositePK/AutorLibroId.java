package com.example.demo.model.compositePK;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class AutorLibroId implements Serializable {
    private Long id_autor;
    private Long id_libro;
}
