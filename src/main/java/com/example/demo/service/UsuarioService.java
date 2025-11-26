package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.model.compositePK.SolicitaAmistad;
import com.example.demo.modelDTO.SolicitaAmistadRequestDTO;
import com.example.demo.modelDTO.UsuarioRequestDTO;
import com.example.demo.repository.ReseniaRepository;
import com.example.demo.repository.SolicitaAmistadRepository;
import com.example.demo.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .pathImagenPerfil(usuario.getPathImagenPerfil())
                .build();
    }
    //Crear un Usuario
    @Transactional
    public ResponseEntity<Usuario> crearUsuario(Usuario usuario){
         usuarioRepository.save(usuario);
         return new ResponseEntity<>(usuario, HttpStatus.OK);
    }


    //Eliminar miUsuario
    @Transactional
    public void eliminarMiUsuario(long idUsuario){
        usuarioRepository.deleteById(idUsuario);
        reseniaRepository.deleteByUsuarioId(idUsuario);
        
    }
}

