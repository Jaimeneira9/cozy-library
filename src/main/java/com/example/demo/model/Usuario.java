package com.example.demo.model;

import com.example.demo.model.compositePK.AmistadId;
import com.example.demo.model.compositePK.SolicitaAmistad;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nombre;
    private String contraseña;
    private LocalDateTime fechaRegistro;
    private String email;
    private String pathImagenPerfil;


    @OneToMany(mappedBy = "usuario")
    List<Resenia> resenias;

    @OneToMany(mappedBy = "usuario")
    List<ListaLectura> listasLectura;

    @OneToMany(mappedBy = "solicitante")
    List<SolicitaAmistad> solicitudesEnviadas;

    @OneToMany(mappedBy = "solicitado")
    List<SolicitaAmistad> solicitudesRecibidas;

    @OneToMany(mappedBy = "usuario1")
    private List<Amistad> amistades1;

    @OneToMany(mappedBy = "usuario2")
    private List<Amistad> amistades2;

}
