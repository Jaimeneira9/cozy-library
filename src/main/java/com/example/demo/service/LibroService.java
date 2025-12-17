package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.modelDTO.LibroRequestDTO;
import com.example.demo.modelDTO.ReseniaRequestDTO;
import com.example.demo.repository.AutorRepository;
import com.example.demo.repository.LibroRepository;
import com.example.demo.repository.ReseniaRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroService {
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    private UsuarioRepository usuarioRepository;

    public LibroService(LibroRepository libroRepository
                        ,AutorRepository autorRepository
                        ,UsuarioRepository usuarioRepository){
        this.libroRepository=libroRepository;
        this.autorRepository=autorRepository;
        this.usuarioRepository=usuarioRepository;
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

    //Mostrar todos los libros por autor
    @Transactional(readOnly = true)
    public List<LibroRequestDTO> getLibroPorAutor(String nombreAutor){
        Optional<Autor> autorOpcional = autorRepository.findByNombreContainingIgnoreCase(nombreAutor);

        if (autorOpcional.isPresent()){
            Autor autorExiste = autorOpcional.get();
            List<AutorLibro> autorLibros = autorExiste.getLibros();
            List<Libro> libros = new ArrayList<>();
            autorLibros.forEach(autorLibro -> libros.add(autorLibro.getLibro()));

            return convertirListaDTO(libros);
        }else return null;


    }
    //Mostrar libros por titulo
    @Transactional(readOnly = true)
    public List<LibroRequestDTO> getLibroPorTitulo(String titulo){

        List<Libro> libros = libroRepository.findByTituloContainingIgnoreCase(titulo);

            return convertirListaDTO(libros);



    }
    //Mostrar libros por añoPublicacion
    @Transactional(readOnly = true)
    public List<LibroRequestDTO> getLibroPorAnioPublicacion(int anioPublicacion){
        List<Libro> libros = libroRepository.findByAnioPublicacion(anioPublicacion);
            return convertirListaDTO(libros);
    }



}
