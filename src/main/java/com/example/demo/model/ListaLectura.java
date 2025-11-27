package com.example.demo.model;

import com.example.demo.model.compositePK.ListaLibroId;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "listaLectura")
public class ListaLectura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fechaCreacion;
    private String nombreLista;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "listaLectura")
    private List<ListaLibro> librosEnLista;


}
