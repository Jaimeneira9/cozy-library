package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.modelDTO.UsuarioRequestDTO;
import com.example.demo.repository.SolicitaAmistadRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private SolicitaAmistadRepository solicitaAmistadRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, SolicitaAmistadRepository solicitaAmistadRepository){
        this.solicitaAmistadRepository=solicitaAmistadRepository;
        this.usuarioRepository=usuarioRepository;
    }
    public UsuarioRequestDTO mapToRequestDTO(Usuario usuario){
        return UsuarioRequestDTO.builder()
                .
                .build();
    }
    //Crear un Usuario
    public Usuario crearUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    //Mostrar amigos
    public List<UsuarioRequestDTO> mostrarAmigos(long idUsuario){

    }
}
