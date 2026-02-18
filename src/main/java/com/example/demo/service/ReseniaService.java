package com.example.demo.service;

import com.example.demo.model.Lectura;
import com.example.demo.model.Libro;
import com.example.demo.model.Resenia;
import com.example.demo.model.Usuario;
import com.example.demo.modelDTO.ReseniaRequestDTO;
import com.example.demo.repository.LecturaRepository;
import com.example.demo.repository.LibroRepository;
import com.example.demo.repository.ReseniaRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReseniaService {

    @Autowired
    private ReseniaRepository resenaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LecturaRepository lecturaRepository;
    @Autowired
    private LibroRepository libroRepository;
    public ReseniaRequestDTO crearResenia(ReseniaRequestDTO dto) {
        // 1. Buscar entidades
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Libro libro = libroRepository.findById(dto.getIdLibro())
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        // 2. Guardar en BD
        Resenia resena = new Resenia();
        resena.setUsuario(usuario);
        resena.setLibro(libro);
        resena.setComentario(dto.getComentario());
        resena.setFecha(LocalDate.now());

        Resenia guardada = resenaRepository.save(resena);

        // 3. Devolver tu DTO usando el Builder
        return convertirADTO(guardada);
    }
    private ReseniaRequestDTO convertirADTO(Resenia resenia) {
        // 1. Obtener nombres de autores (a través de la tabla intermedia AutorLibro)
        List<String> nombres = resenia.getLibro().getAutores().stream()
                .map(autorLibro -> autorLibro.getAutor().getNombre())
                .collect(Collectors.toList());

        // 2. BUSCAR LA VALORACIÓN EN LECTURA
        // Buscamos la lectura de ese usuario para ese libro específico
        Optional<Lectura> lecturaOpt = lecturaRepository.findByUsuarioIdAndLibroId(
                resenia.getUsuario().getId(),
                resenia.getLibro().getId()
        );

        // Si la lectura existe, sacamos la valoración. Si no, ponemos 0.0
        double valoracionLectura = lecturaOpt
                .map(l -> (double) l.getValoracion()) // Convertimos Integer a double para el DTO
                .orElse(0.0);

        // 3. Construir el DTO con el Builder
        return ReseniaRequestDTO.builder()
                .idLibro(resenia.getLibro().getId())
                .idUsuario(resenia.getUsuario().getId())
                .urlPortada(resenia.getLibro().getUrlPortada())
                .nombreUsuario(resenia.getUsuario().getNombre())
                .tituloLibro(resenia.getLibro().getTitulo())
                .nombresAutores(nombres)
                .fecha(resenia.getFecha())
                .comentario(resenia.getComentario())
                .valoracion(valoracionLectura) // <--- Aquí inyectamos la nota de la tabla Lectura
                .build();
    }

    public List<ReseniaRequestDTO> obtenerReseniasAmigos(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Usamos el nuevo método que une las dos listas
        List<Long> idsAmigos = usuario.getAmigosTotales().stream()
                .map(Usuario::getId)
                .collect(Collectors.toList());

        if (idsAmigos.isEmpty()) return new ArrayList<>();

        return resenaRepository.findByUsuarioIdInOrderByFechaDesc(idsAmigos)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
}

