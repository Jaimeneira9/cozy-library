package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.modelDTO.UsuarioRequestDTO;
import com.example.demo.repository.ReseniaRepository;
import com.example.demo.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private ReseniaRepository reseniaRepository;


    public UsuarioService(UsuarioRepository usuarioRepository, ReseniaRepository reseniaRepository){
        this.reseniaRepository=reseniaRepository;
        this.usuarioRepository=usuarioRepository;
    }
    public UsuarioRequestDTO mapToRequestDTO(Usuario usuario){
        return UsuarioRequestDTO.builder()
                .nombre(usuario.getNombre())

                .email(usuario.getEmail())
                .fechaRegistro(usuario.getFechaRegistro())
                .url_imagen_perfil(usuario.getUrl_imagen_perfil())
                .valoraciones(usuario.getResenias().size())
                .build();
    }
    //Crear un Usuario
    @Transactional
    public String crearUsuario(Usuario usuario){
        usuario.setFechaRegistro(LocalDateTime.now());
         usuarioRepository.save(usuario);
         return "Usuario creado con exito";
    }


}

