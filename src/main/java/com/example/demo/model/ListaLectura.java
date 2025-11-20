package com.example.demo.model;

import com.example.demo.model.compositePK.ListaLibro;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class ListaLectura {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate fechaCreacion;
    private String nombreLista;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "listalectura")
    private List<ListaLibro> libros;


}
