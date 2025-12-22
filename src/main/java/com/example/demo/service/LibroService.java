package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.model.compositePK.AutorLibroId;
import com.example.demo.model.googleBooks.Item;
import com.example.demo.model.googleBooks.VolumeInfo;
import com.example.demo.modelDTO.GoogleBooksResponseDTO;
import com.example.demo.modelDTO.LibroRequestDTO;
import com.example.demo.repository.*;
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

    public LibroRequestDTO mapToRequestDTO(Libro libro){
            List<AutorLibro> autoresLibro = libro.getAutores();
            List<String> nombreAutores = new ArrayList<>();
            for(AutorLibro autorLibro:autoresLibro){
                nombreAutores.add(autorLibro.getAutor().getNombre());
            }
        return LibroRequestDTO.builder()
                .titulo(libro.getTitulo())
                .anyoPublicacion(libro.getAnioPublicacion())
                .urlPortada(libro.getUrlPortada())
                .autores(nombreAutores)
                .build();
    }
    public List<LibroRequestDTO> convertirListaDTO(List<Libro> libros){
        return libros.stream()
                .map(this::mapToRequestDTO)
                .collect(Collectors.toList());
    }




    public List<LibroRequestDTO> buscarLibros(String autor, String titulo, String anio) {

        if (autor != null) {
            return convertirListaDTO(libroRepository.findByAutorNombre(autor));
        }

        if (titulo != null) {
            return convertirListaDTO(libroRepository.findByTituloContainingIgnoreCase(titulo));
        }

        if (anio != null) {
            return convertirListaDTO(libroRepository.findByAnioPublicacion(anio));
        }

        // Si no viene ningún filtro → devolver todos
        return convertirListaDTO(libroRepository.findAll());
    }
    //Parsea la fecha para extraer solo el año
    private int extraerAnio(String fecha) {
        if (fecha == null || fecha.length() < 4) return 0;
        return Integer.parseInt(fecha.substring(0, 4));
    }

    public void importarLibros(String nombreAutor, String titulo, String palabrasClave) {
        //Las busquedas de google se hacen en tandas de 40
        int startIndex = 0;
        int maxResults = 40;

        // Construir query dinámicamente
        StringBuilder query = new StringBuilder();
        if (nombreAutor != null && !nombreAutor.isBlank()) {
            query.append("inauthor:").append(nombreAutor.trim().replace(" ", "+"));
        }
        if (titulo != null && !titulo.isBlank()) {
            if (!query.isEmpty()) query.append("+");
            query.append("intitle:").append(titulo.trim().replace(" ", "+"));
        }
        if (palabrasClave != null && !palabrasClave.isBlank()) {
            if (!query.isEmpty()) query.append("+");
            query.append(palabrasClave.trim().replace(" ", "+"));
        }

        GoogleBooksResponseDTO response;

        do {
            String url = "https://www.googleapis.com/books/v1/volumes?q=" + query
                    + "&startIndex=" + startIndex + "&maxResults=" + maxResults;

            response = restTemplate.getForObject(url, GoogleBooksResponseDTO.class);

            if (response.getItems() != null) {
                for (Item item : response.getItems()) {
                    VolumeInfo volumeInfo = item.getVolumeInfo();

                    // Evitar duplicados
                    if (volumeInfo.getTitle() == null) continue;
                    if (libroRepository.existsByTituloAndAnioPublicacion(volumeInfo.getTitle(), volumeInfo.getPublishedDate()))
                        continue;

                    // Crear libro
                    Libro libro = new Libro();
                    libro.setTitulo(volumeInfo.getTitle());
                    libro.setAnioPublicacion(volumeInfo.getPublishedDate());
                    libro.setUrlPortada(volumeInfo.getImageLinks() != null ? volumeInfo.getImageLinks().getThumbnail() : null);
                    libro = libroRepository.save(libro);

                    // Autores
                    if (volumeInfo.getAuthors() != null) {
                        for (String nombre : volumeInfo.getAuthors()) {
                            Autor autor = autorRepository.findByNombreContainingIgnoreCase(nombreAutor)
                                    .orElseGet(() -> autorRepository.save(new Autor(nombreAutor)));

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

            startIndex += maxResults;
        } while (response.getTotalItems() > startIndex);
    }



}
