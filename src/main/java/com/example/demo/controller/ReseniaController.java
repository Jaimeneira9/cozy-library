package com.example.demo.controller;

import com.example.demo.modelDTO.ReseniaRequestDTO;
import com.example.demo.service.ReseniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/resenias")
@RestController
public class ReseniaController {
    @Autowired
    private ReseniaService reseniaService;

    // Publicar comentario: POST /api/resenias/publicar
    // El cuerpo (Body) debe ser un JSON con: usuarioId, libroId, comentario y valoracion
    @PostMapping("/publicar")
    public ResponseEntity<ReseniaRequestDTO> publicar(@RequestBody ReseniaRequestDTO reseniaDto) {
        // El service ahora devuelve el DTO que tú tienes creado
        return ResponseEntity.ok(reseniaService.crearResenia(reseniaDto));
    }

    // El Muro de Amigos (Figma): GET /api/resenias/muro/1
    @GetMapping("/muro/{idUsuario}")
    public ResponseEntity<List<ReseniaRequestDTO>> obtenerMuro(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(reseniaService.obtenerReseniasAmigos(idUsuario));
    }
}
