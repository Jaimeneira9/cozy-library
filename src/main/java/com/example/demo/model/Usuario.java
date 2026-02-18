package com.example.demo.model;


import com.example.demo.model.compositePK.SolicitaAmistad;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity

@Table(name = "usuarios")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private long id;
    private String nombre;
    private String contraseña;
    private LocalDateTime fechaRegistro;
    private String email;
    private String url_imagen_perfil;


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

    // Este método "une" las dos listas para darte todos los amigos de verdad
    public List<Usuario> getAmigosTotales() {
        List<Usuario> amigos = new ArrayList<>();

        // De la lista 1, el amigo es el "usuario2"
        if (amistades1 != null) {
            for (Amistad a : amistades1) {
                amigos.add(a.getUsuario2());
            }
        }

        // De la lista 2, el amigo es el "usuario1"
        if (amistades2 != null) {
            for (Amistad a : amistades2) {
                amigos.add(a.getUsuario1());
            }
        }

        return amigos;
    }

}
