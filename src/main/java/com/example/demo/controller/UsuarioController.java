package com.example.demo.controller;

import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
    private UsuarioService usuarioService;
    @Autowired
    public UsuarioController (UsuarioService usuarioService){
        this.usuarioService=usuarioService;
    }
}
