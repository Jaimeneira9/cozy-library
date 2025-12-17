package com.example.demo.controller;

import com.example.demo.modelDTO.LibroRequestDTO;
import com.example.demo.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibroController {
    private LibroService libroService;
    @Autowired
    public LibroController(LibroService libroService){
        this.libroService=libroService;
    }

    @GetMapping("/libros/buscarPorAutor/{autor}")
    //Mostrar libros por autor
    public List<LibroRequestDTO>  mostrarLibrosPorAutor(@PathVariable  String nombreAutor){
        return libroService.getLibroPorAutor(nombreAutor);
    }//Mostrar libros por titulo
    @GetMapping("/libros/buscarPorTitulo/{titulo}")
    public List<LibroRequestDTO>  mostrarLibrosPorTitulo(@PathVariable  String titulo){
        return libroService.getLibroPorTitulo(titulo);
    }//Mostrar libros por anioPublicacion
    @GetMapping("/libros/buscarPorAnio/{anioPubliacion}")
    public List<LibroRequestDTO>  mostrarLibrosPorAnioPublicacion(@PathVariable  int anioPublicacion){
        return libroService.getLibroPorAnioPublicacion(anioPublicacion);
    }
}
