package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
@Table(name = "lecturas")
public class Lectura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double porcentajeLeido;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
