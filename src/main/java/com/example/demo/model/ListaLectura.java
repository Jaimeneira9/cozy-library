package com.example.demo.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity

@Table(name = "lista_lectura")

public class ListaLectura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
    @Column(name = "nombre_lista")
    private String nombreLista;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "listaLectura")
    private List<ListaLibro> librosEnLista;


}
