package com.example.demo.model;


import com.example.demo.model.compositePK.SolicitaAmistad;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
<<<<<<< HEAD
@Table(name = "usuarios")
=======
@Table(name = "usuario")
>>>>>>> e9bb205ffd7bb5bcb564a6a3591819a20b1c6b3e
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
