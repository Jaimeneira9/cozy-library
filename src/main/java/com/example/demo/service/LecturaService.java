package com.example.demo.service;

import com.example.demo.model.*;

@Service
public class LecturaService {

    @Autowired
    private LecturaRepository lecturaRepository;

    public empezarLectura(){
        Lectura lectura = new Lectura();
        lectura.setFechaInicio = LocalDate.now()
        
    }
}