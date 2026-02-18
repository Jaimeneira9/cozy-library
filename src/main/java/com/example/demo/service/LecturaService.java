package com.example.demo.service;

import com.example.demo.model.Lectura;
import com.example.demo.model.Libro;
import com.example.demo.repository.LecturaRepository;
import com.example.demo.repository.LibroRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class LecturaService {


    @Autowired
    private LecturaRepository lecturaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LibroRepository libroRepository;

    // Faltaba el tipo de retorno "Lectura" y corregir la lógica
    public Lectura empezarLectura(long idUsuario, long idLibro) {
        Lectura lectura = new Lectura();
        lectura.setFechaInicio(LocalDate.now());
        lectura.setUsuario(usuarioRepository.findById(idUsuario).orElse(null));
        lectura.setLibro(libroRepository.findById(idLibro).orElse(null));
        lectura.setPaginas_actuales(0);
        lectura.setPorcentajeLeido(0);

        // Aquí asignaríamos los IDs o buscaríamos los objetos
        // Por ahora, para que compile:
        return lecturaRepository.save(lectura);
    }

    public Lectura actualizarLectura(long idLectura, int nuevaPagina) {
        // 1. Buscamos la lectura existente
        Lectura lectura = lecturaRepository.findById(idLectura)
                .orElseThrow(() -> new RuntimeException("No se encontró la lectura con ID: " + idLectura));

        // 2. Obtenemos el libro para saber el total de páginas
        Libro libro = lectura.getLibro();

        // 3. Actualizamos la página actual
        lectura.setPaginas_actuales(nuevaPagina);

        // 4. Calculamos el porcentaje automáticamente
        if (libro != null && libro.getNumPaginas() > 0) {
            double porcentaje = (nuevaPagina * 100.0) / libro.getNumPaginas();

            lectura.setPorcentajeLeido(Math.min(porcentaje, 100.0));

            // 5. ¿Terminó el libro?
            if (nuevaPagina >= libro.getNumPaginas()) {
                lectura.setFechaFin(LocalDate.now());
                lectura.setEstado("TERMINADO");
            } else {
                lectura.setEstado("LEYENDO");
            }
        }

        // 6. Guardamos los cambios
        return lecturaRepository.save(lectura);
    }
    public Lectura puntuarLectura(Long lecturaId, Integer estrellas) {
        Lectura lectura = lecturaRepository.findById(lecturaId)
                .orElseThrow(() -> new RuntimeException("No se encontró la lectura"));

        // Guardamos la valoración (1-5)
        lectura.setValoracion(estrellas);

        // Aprovechamos para marcarla como terminada si no lo estaba
        lectura.setEstado("TERMINADO");
        lectura.setFechaFin(LocalDate.now());

        return lecturaRepository.save(lectura);
    }
    public Map<String, Object> obtenerEstadisticasUsuario(Long usuarioId) {
        Map<String, Object> stats = new HashMap<>();

        // 1. Total de páginas: Sumamos 'paginaActual' de todas sus lecturas
        Long totalPaginas = lecturaRepository.sumarTotalPaginasLeidas(usuarioId);

        // 2. Libros terminados: Contamos registros con estado 'TERMINADO'
        Long librosTerminados = lecturaRepository.contarLibrosTerminados(usuarioId);

        // 3. Valoración media: Promedio de sus puntuaciones
        Double mediaValoracion = lecturaRepository.obtenerRatingPromedio(usuarioId);

        // Lógica para evitar nulos y formatear la media
        stats.put("paginasLeidas", totalPaginas != null ? totalPaginas : 0);
        stats.put("librosTerminados", librosTerminados != null ? librosTerminados : 0);

        // Redondeamos a un decimal (ej: 4.6666 -> 4.7) para que quede elegante como en Figma
        double mediaFormateada = (mediaValoracion != null) ? Math.round(mediaValoracion * 10.0) / 10.0 : 0.0;
        stats.put("ratingPromedio", mediaFormateada);

        return stats;
    }
}