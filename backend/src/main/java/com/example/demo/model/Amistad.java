package com.example.demo.model;

import com.example.demo.model.compositePK.AmistadId;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="amistades")

public class Amistad {
    @EmbeddedId
    private AmistadId id;

    @ManyToOne
    @JoinColumn(name = "usuario1_id", insertable = false, updatable = false)
    private Usuario usuario1;

    @ManyToOne
    @JoinColumn(name = "usuario2_id", insertable = false, updatable = false)
    private Usuario usuario2;
    @Column(name = "fecha_amistad")
    private LocalDate fechaAmistad;
}
