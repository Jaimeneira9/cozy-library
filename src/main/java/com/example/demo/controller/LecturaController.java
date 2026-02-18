package com.example.demo.controller;

import com.example.demo.model.Lectura;
import com.example.demo.service.LecturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/lecturas")
@CrossOrigin(origins = "*") // Permite que Angular se conecte
public class LecturaController {

    @Autowired
    private LecturaService lecturaService;

    // Empezar un libro: POST /api/lecturas/empezar?idUsuario=1&idLibro=10
    @PostMapping("/empezar")
    public ResponseEntity<Lectura> empezar(@RequestParam Long idUsuario, @RequestParam Long idLibro) {
        return ResponseEntity.ok(lecturaService.empezarLectura(idUsuario, idLibro));
    }

    // Actualizar página: PUT /api/lecturas/5/progreso?pagina=120
    @PutMapping("/{id}/progreso")
    public ResponseEntity<Lectura> actualizar(@PathVariable Long id, @RequestParam int pagina) {
        return ResponseEntity.ok(lecturaService.actualizarLectura(id, pagina));
    }

    // Finalizar y dar estrellas: PUT /api/lecturas/5/finalizar?estrellas=5
    @PutMapping("/{id}/finalizar")
    public ResponseEntity<Lectura> finalizar(@PathVariable Long id, @RequestParam Integer estrellas) {
        return ResponseEntity.ok(lecturaService.puntuarLectura(id, estrellas));
    }

    // Stats para el perfil de Figma: GET /api/lecturas/usuario/1/stats
    @GetMapping("/usuario/{idUsuario}/stats")
    public ResponseEntity<Map<String, Object>> getStats(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(lecturaService.obtenerEstadisticasUsuario(idUsuario));
    }
}
