package com.example.demo.model;

import com.example.demo.model.compositePK.AmistadId;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="amitades")

public class Amistad {
    @EmbeddedId
    private AmistadId id;

    @ManyToOne
    @JoinColumn(name = "usuario1_id")
    private Usuario usuario1;

    @ManyToOne
    @JoinColumn(name = "usuario2_id")
    private Usuario usuario2;
    @Column(name = "fecha_amistad")
    private LocalDate fechaAmistad;
}
