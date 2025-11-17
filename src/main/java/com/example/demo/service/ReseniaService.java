package com.example.demo.service;

import com.example.demo.model.Libro;
import com.example.demo.model.Resenia;
import com.example.demo.model.Usuario;
import com.example.demo.modelDTO.ReseniaRequestDTO;
import com.example.demo.repository.AutorRepository;
import com.example.demo.repository.LibroRepository;
import com.example.demo.repository.ReseniaRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReseniaService {

    private final LibroRepository libroRepository;
    private final UsuarioRepository usuarioRepository;
    private final ReseniaRepository reseniaRepository;

    public ReseniaService(LibroRepository libroRepository,
                          UsuarioRepository usuarioRepository,
                          ReseniaRepository reseniaRepository) {
        this.libroRepository = libroRepository;
        this.usuarioRepository = usuarioRepository;
        this.reseniaRepository = reseniaRepository;
    }

    @Transactional
    public Resenia crearResenia(Long idLibro, ReseniaRequestDTO dto) {

        Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        Usuario usuario = usuarioRepository.findByNombreContainingIgnoreCase(dto.getNombreUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Resenia resenia = new Resenia();
        resenia.setLibro(libro);
        resenia.setUsuario(usuario);
        resenia.setComentario(dto.getComentario());
        resenia.setFecha(dto.getFecha());
        resenia.setValoracion(dto.getValoracion());

        return reseniaRepository.save(resenia);
    }
}

