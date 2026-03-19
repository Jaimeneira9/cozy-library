package com.example.demo.service;


import com.example.demo.model.compositePK.SolicitaAmistad;
import com.example.demo.modelDTO.SolicitaAmistadRequestDTO;
import com.example.demo.repository.SolicitaAmistadRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SolicitaAmistadService {
    private SolicitaAmistadRepository solicitaAmistadRepository;

    public SolicitaAmistadService(SolicitaAmistadRepository solicitaAmistadRepository) {
        this.solicitaAmistadRepository = solicitaAmistadRepository;
    }

    public SolicitaAmistadRequestDTO mapToRequestDTO(SolicitaAmistad solicitud) {
        return SolicitaAmistadRequestDTO.builder()
                .nombreSolicitado(solicitud.getSolicitado().getNombre())
                .nombreSolicitante(solicitud.getSolicitante().getNombre())
                .estadoSolicitud(solicitud.getEstadoSolicitud())
                .fechaSolicitud(solicitud.getFechaSolicitud())
                .build();
    }

    public List<SolicitaAmistadRequestDTO> convertirListaDTO(List<SolicitaAmistad> solicitudes) {
        return solicitudes.stream().map(this::mapToRequestDTO).collect(Collectors.toList());
    }

    //Mostrar Solicitudes Recibidas
    @Transactional
    public List<SolicitaAmistadRequestDTO> mostrarSolicitudesRecibidas(long id) {
        return convertirListaDTO(solicitaAmistadRepository.findBySolicitadoId(id));
    }

    //Mostrar Solicitudes Enviadas
    @Transactional
    public List<SolicitaAmistadRequestDTO> mostrarSolicitudesEnviadas(long id) {
        return convertirListaDTO(solicitaAmistadRepository.findBySolicitanteId(id));
    }

    //Aceptar o Rechazar Solicitud
    @Transactional
    public ResponseEntity<SolicitaAmistad> cambiarEstadoSolicitud(long idSolicitud, boolean estado) {
        Optional<SolicitaAmistad> solicitudOpcional = solicitaAmistadRepository.findById(idSolicitud);
        if (solicitudOpcional.isPresent()) {
            SolicitaAmistad solicitud = solicitudOpcional.get();
            solicitud.setEstadoSolicitud(estado ? "Aceptada" : "Rechazada");
            SolicitaAmistadRequestDTO dto = mapToRequestDTO(solicitud);
            return new ResponseEntity(dto, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();

    }
}

