package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/usuarios")
@RestController
public class UsuarioController {
    private UsuarioService usuarioService;
    @Autowired
    public UsuarioController (UsuarioService usuarioService){
        this.usuarioService=usuarioService;
    }

    @PostMapping("/registro")
    public ResponseEntity<String> registroUsuario(@RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioService.crearUsuario(usuario), HttpStatus.OK);
    }
}
