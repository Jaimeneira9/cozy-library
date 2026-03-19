package com.example.demo.service;

import com.example.demo.model.Autor;
import com.example.demo.model.AutorLibro;
import com.example.demo.model.Libro;
import com.example.demo.model.compositePK.AutorLibroId;
import com.example.demo.modelDTO.InfoLibroDTO;
import com.example.demo.repository.AutorLibroRepository;
import com.example.demo.repository.AutorRepository;
import com.example.demo.repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class LibroService {

    private LibroRepository libroRepository;
    private GoogleBooksService googleBooksService;
    private AutorRepository autorRepository;
    private AutorLibroRepository autorLibroRepository;
    private final RestTemplate restTemplate;

    public LibroService(LibroRepository libroRepository
                        ,GoogleBooksService googleBooksService,
                        AutorRepository autorRepository,
                        AutorLibroRepository autorLibroRepository){
        this.libroRepository=libroRepository;
        this.googleBooksService=googleBooksService;
        this.autorRepository=autorRepository;
        this.autorLibroRepository=autorLibroRepository;
        this.restTemplate = new RestTemplate();

    }






    public List<InfoLibroDTO> buscarLibrosHibrido(String query) {
        // 1. Buscamos primero en nuestra base de datos (Supabase)
        List<Libro> librosEnBD = libroRepository.findByTituloContainingIgnoreCase(query);

        // Convertimos lo que hay en BD a InfoLibroDTO
        List<InfoLibroDTO> resultados = librosEnBD.stream().map(libro -> {
            InfoLibroDTO dto = new InfoLibroDTO();
            dto.setTitulo(libro.getTitulo());
            dto.setGoogleId(libro.getGoogleId());
            dto.setAnio(libro.getAnioPublicacion());
            dto.setPortada(libro.getUrlPortada());
            dto.setStored(true); // Marcamos que ya existe en nuestra base de datos
            return dto;
        }).collect(Collectors.toCollection(ArrayList::new));

        // 2. Si hay pocos resultados locales, llamamos a Python para traer de Google Books
        if (resultados.size() < 5) {
            List<InfoLibroDTO> desdePython = googleBooksService.buscarLibrosEnPython(query);

            for (InfoLibroDTO nuevo : desdePython) {
                // Solo añadimos si no existe ya un libro con el mismo título en la lista
                boolean yaExiste = resultados.stream()
                        .anyMatch(r -> r.getTitulo().equalsIgnoreCase(nuevo.getTitulo()));

                if (!yaExiste) {
                    nuevo.setStored(false); // Estos no están guardados todavía
                    resultados.add(nuevo);
                }
            }
        }
        return resultados;
    }
    //Parsea la fecha para extraer solo el año
    private int extraerAnio(String fecha) {
        if (fecha == null || fecha.length() < 4) return 0;
        return Integer.parseInt(fecha.substring(0, 4));
    }

    public void guardarLibroSeleccionado(InfoLibroDTO dto) {
        // Verificación de seguridad: ¿Existe ya?
        if (libroRepository.existsByTituloAndAnioPublicacion(dto.getTitulo(), dto.getAnio())) {
            return;
        }

        // Crear la entidad Libro
        Libro libro = new Libro();
        libro.setTitulo(dto.getTitulo());
        libro.setAnioPublicacion(dto.getAnio());
        libro.setUrlPortada(dto.getPortada());
        libro = libroRepository.save(libro);

        // Guardar Autores y relación AutorLibro
        if (dto.getAutores() != null) {
            for (String nombre : dto.getAutores()) {
                Autor autor = autorRepository.findByNombreContainingIgnoreCase(nombre)
                        .orElseGet(() -> autorRepository.save(new Autor(nombre)));

                AutorLibro autorLibro = new AutorLibro();
                AutorLibroId id = new AutorLibroId();
                id.setIdAutor(autor.getId());
                id.setIdLibro(libro.getId());

                autorLibro.setId(id);
                autorLibro.setAutor(autor);
                autorLibro.setLibro(libro);

                autorLibroRepository.save(autorLibro);
            }
        }
    }



}
