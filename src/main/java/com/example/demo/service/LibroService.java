package com.example.demo.service;

import com.example.demo.model.Autor;
import com.example.demo.model.AutorLibro;
import com.example.demo.model.Libro;
import com.example.demo.modelDTO.LibroRequestDTO;
import com.example.demo.repository.AutorRepository;
import com.example.demo.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroService {
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public LibroService(LibroRepository libroRepository,AutorRepository autorRepository){
        this.libroRepository=libroRepository;
        this.autorRepository=autorRepository;
    }
    public LibroRequestDTO mapToRequestDTO(Libro libro){
        return LibroRequestDTO.builder()
                .titulo(libro.getTitulo())
                .anyoPublicacion(libro.getAnyoPublicacion())
                .urlPortada(libro.getUrlPortada())
                .autores(libro.getAutores())
                .build();
    }
    public List<LibroRequestDTO> convertirListaDTO(List<Libro> libros){
        return libros.stream()
                .map(this::mapToRequestDTO)
                .collect(Collectors.toList());
    }

    //Mostrar todos los libros por autor
    public List<LibroRequestDTO> getLibroPorAutor(String nombreAutor){
        Optional<Autor> autorOpcional = autorRepository.findByNombre(nombreAutor);

        if (autorOpcional.isPresent()){
            Autor autorExiste = autorOpcional.get();
            List<AutorLibro> autorlibros = autorExiste.getLibros();
            List<Libro> libros = new ArrayList<>();
            for(AutorLibro autorLibro:autorlibros){
                libros.add(autorLibro.getLibro());
            }
            return convertirListaDTO(libros);
        }else return null;


    }
}
