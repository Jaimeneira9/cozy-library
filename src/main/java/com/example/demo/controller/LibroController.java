package com.example.demo.controller;

import com.example.demo.modelDTO.LibroRequestDTO;
import com.example.demo.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibroController {
    private LibroService libroService;
    @Autowired
    public LibroController(LibroService libroService){
        this.libroService=libroService;
    }

    @GetMapping("/libros")
    public List<LibroRequestDTO> buscarLibros(
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String anio
    ) {
        return libroService.buscarLibros(autor, titulo, anio);

    }
    @PostMapping("/libros/importar")
    public void importar(@RequestParam(required = false) String autor,
                         @RequestParam(required = false) String titulo,
                         @RequestParam(required = false) String palabrasClave) {
        libroService.importarLibros(autor, titulo, palabrasClave);
    }


}
