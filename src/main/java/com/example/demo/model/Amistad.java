package com.example.demo.model;

import com.example.demo.model.compositePK.AmistadId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Amistad {
    @EmbeddedId
    private AmistadId id;

    @ManyToOne
    @JoinColumn(name = "usuario1_id")
    private Usuario usuario1;

    @ManyToOne
    @JoinColumn(name = "usuario2_id")
    private Usuario usuario2;

    private LocalDate fechaAmistad;
}
