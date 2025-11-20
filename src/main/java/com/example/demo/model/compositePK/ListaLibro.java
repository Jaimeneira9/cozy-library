package com.example.demo.model.compositePK;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class ListaLibro implements Serializable {
    private Long id_libro;
    private Long id_lista;
}
