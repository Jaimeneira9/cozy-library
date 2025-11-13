package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_autor")
    private Long id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String nacionalidad;

    @OneToMany(mappedBy ="autor")
    private List<AutorLibro> libros;

}
