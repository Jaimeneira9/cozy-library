package com.example.demo.model.compositePK;

import com.example.demo.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class SolicitaAmistad  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name="solicitante_id")
    private Usuario solicitante;

    @ManyToOne
    @JoinColumn(name="solicitado_id")
    private Usuario solicitado;

    private LocalDate fechaSolicitud;

    private String estadoSolicitud;


}
