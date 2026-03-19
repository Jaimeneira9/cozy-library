package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.modelDTO.UsuarioRequestDTO;
import com.example.demo.repository.AmistadRepository;
import com.example.demo.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AmistadService {
    private AmistadRepository amistadRepository;
    private UsuarioRepository usuarioRepository;
    public AmistadService(AmistadRepository amistadRepository,UsuarioRepository usuarioRepository){
        this.amistadRepository=amistadRepository;
        this.usuarioRepository=usuarioRepository;
    }
    public UsuarioRequestDTO mapToRequestDTO(Usuario usuario){
        return UsuarioRequestDTO.builder()
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .fechaRegistro(usuario.getFechaRegistro())
                .url_imagen_perfil(usuario.getUrl_imagen_perfil())
                .build();
    }
    public List<UsuarioRequestDTO> convertirListaDTO(List<Usuario> usuarios){
        return usuarios.stream().map(this::mapToRequestDTO).collect(Collectors.toList());
    }
    //Mostrar Amigos
    @Transactional
    public List<UsuarioRequestDTO> mostrarAmigos(long idUsuario){
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        Usuario usuarioExiste = usuario.get();
        return usuarioExiste.getAmigosTotales().size()>0 ? convertirListaDTO(usuarioExiste.getAmigosTotales()) : null;

    }
    //Eliminar Amigos
    @Transactional
    public void eliminarAmigo(long idUsuario,long idAmigo){
        amistadRepository.eliminarAmistad(idUsuario,idAmigo);
    }
}
