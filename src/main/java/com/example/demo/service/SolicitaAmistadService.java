package com.example.demo.service;

import com.example.demo.repository.SolicitaAmistadRepository;
import org.springframework.stereotype.Service;

@Service
public class SolicitaAmistadService {
    private SolicitaAmistadRepository solicitaAmistadRepository;

    public SolicitaAmistadService(SolicitaAmistadRepository solicitaAmistadRepository){
        this.solicitaAmistadRepository=solicitaAmistadRepository;
    }
}
