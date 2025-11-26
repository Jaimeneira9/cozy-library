package com.example.demo.modelDTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SolicitaAmistadRequestDTO {
    private String nombreSolicitante;
    private String nombreSolicitado;
    private String estadoSolicitud;
    private LocalDate fechaSolicitud;
}
