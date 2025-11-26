package com.example.demo.service;

import com.example.demo.repository.AmistadRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AmistadService {
    private AmistadRepository amistadRepository;
    public AmistadService(AmistadRepository amistadRepository){
        this.amistadRepository=amistadRepository;
    }

    //Mostrar Amigos
    @Transactional
    public ResponseEntity<String> mostrarAmigos(){

    }
    //Eliminar Amigos
    @Transactional
    public ResponseEntity<String> eliminarAmigo(long idAmigo){

    }
}
