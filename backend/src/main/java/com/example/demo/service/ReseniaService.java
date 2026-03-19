package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.model.compositePK.AutorLibroId;
import com.example.demo.modelDTO.ReseniaRequestDTO;
import com.example.demo.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReseniaService {

    @Autowired
    private ReseniaRepository reseniaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LecturaRepository lecturaRepository;
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private AutorLibroRepository autorLibroRepository;
    private ReseniaRequestDTO convertirADTO(Resenia resenia) {
        // Ya no explotará porque hemos hecho el 'refresh' arriba
        List<String> nombres = resenia.getLibro().getAutores().stream()
                .map(al -> al.getAutor().getNombre())
                .collect(Collectors.toList());

        Optional<Lectura> lecturaOpt = lecturaRepository.findByUsuarioIdAndLibroId(
                resenia.getUsuario().getId(),
                resenia.getLibro().getId()
        );

        double valoracionLectura = lecturaOpt
                .map(l -> (double) l.getValoracion())
                .orElse(0.0);

        return ReseniaRequestDTO.builder()
                .idLibro(resenia.getLibro().getId())
                .idUsuario(resenia.getUsuario().getId())
                .urlPortada(resenia.getLibro().getUrlPortada())
                .nombreUsuario(resenia.getUsuario().getNombre())
                .tituloLibro(resenia.getLibro().getTitulo())
                .nombresAutores(nombres)
                .fecha(resenia.getFecha())
                .comentario(resenia.getComentario())
                .valoracion(valoracionLectura)
                .build();
    }
    @Transactional

    public ReseniaRequestDTO guardarResenia(ReseniaRequestDTO dto) {
        // 1. Obtener o crear Libro
        // 2. Buscamos o creamos el libro (Forma robusta con Setters)
        Libro libro = libroRepository.findByGoogleId(dto.getGoogleId())
                .orElseGet(() -> {
                    Libro nuevo = new Libro(); // Constructor vacío
                    nuevo.setGoogleId(dto.getGoogleId());
                    nuevo.setTitulo(dto.getTituloLibro());
                    nuevo.setUrlPortada(dto.getUrlPortada());

                    // Si tienes más campos como descripción o fecha de publicación:
                    // nuevo.setDescripcion(dto.getDescripcion());

                    return libroRepository.save(nuevo);
                });
        // Busca al usuario que está creando la reseña usando el ID del DTO
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));
        // 2. Lógica de autores (Ejemplo de cómo hacerlo robusto)
        if (dto.getNombresAutores() != null) {
            for (String nombre : dto.getNombresAutores()) {
                // A. Aseguramos el Autor
                Autor autor = autorRepository.findByNombre(nombre)
                        .orElseGet(() -> {
                            Autor n = new Autor();
                            n.setNombre(nombre);
                            return autorRepository.save(n);
                        });

                // B. CREAMOS LA LLAVE COMPUESTA (Aquí estaba el fallo de tu imagen)
                AutorLibroId pk = new AutorLibroId();
                pk.setIdLibro(libro.getId()); // Asignamos el ID del libro
                pk.setIdAutor(autor.getId()); // Asignamos el ID del autor

                // C. Creamos la relación y le pasamos la llave
                AutorLibro relacion = new AutorLibro();
                relacion.setId(pk); // 👈 IMPORTANTE: Le pasamos la PK compuesta
                relacion.setLibro(libro);
                relacion.setAutor(autor);

                autorLibroRepository.save(relacion);

                // Sincronizamos memoria
                libro.getAutores().add(relacion);
            }
        }

        Resenia resenia = new Resenia();
        resenia.setUsuario(usuario);
        resenia.setLibro(libro);
        resenia.setComentario(dto.getComentario());
        resenia.setFecha(dto.getFecha());

        // Si tu DTO de entrada trae la valoración y quieres guardarla también en la reseña:
        // resenia.setValoracion(dto.getValoracion());

        Resenia guardada = reseniaRepository.save(resenia);

        // 4. Convertir (Ahora libro.getAutores() ya tiene los datos en memoria)
        return convertirADTO(guardada);
    }





    public List<ReseniaRequestDTO> obtenerReseniasAmigos(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Usamos el nuevo método que une las dos listas
        List<Long> idsAmigos = usuario.getAmigosTotales().stream()
                .map(Usuario::getId)
                .collect(Collectors.toList());

        if (idsAmigos.isEmpty()) return new ArrayList<>();

        return reseniaRepository.findByUsuarioIdInOrderByFechaDesc(idsAmigos)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
}

