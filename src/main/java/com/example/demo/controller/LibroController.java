package com.example.demo.controller;

import com.example.demo.modelDTO.InfoLibroDTO;
import com.example.demo.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/libros")
@RestController
public class LibroController {
    private LibroService libroService;
    @Autowired
    public LibroController(LibroService libroService){
        this.libroService=libroService;
    }

    // 1. Endpoint de búsqueda híbrida (DB + Python)
    @GetMapping("/buscar")
    public List<InfoLibroDTO> buscarLibros(@RequestParam String query) {
        // Este método ahora hace todo el trabajo sucio:
        // Busca en Supabase, si falta, llama a Python, limpia y combina.
        return libroService.buscarLibrosHibrido(query);
    }

    // 2. Endpoint para guardar el libro que el usuario elija en Angular
    @PostMapping("/guardar")
    public ResponseEntity<String> guardar(@RequestBody InfoLibroDTO libroDTO) {
        // Recibimos el objeto completo desde el Front-end
        libroService.guardarLibroSeleccionado(libroDTO);
        return ResponseEntity.ok("Libro guardado con éxito en tu biblioteca");
    }



}
