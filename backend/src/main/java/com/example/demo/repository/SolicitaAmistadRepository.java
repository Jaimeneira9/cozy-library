package com.example.demo.repository;

import com.example.demo.model.Usuario;
import com.example.demo.model.compositePK.SolicitaAmistad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitaAmistadRepository extends JpaRepository<SolicitaAmistad,Long> {
    List<SolicitaAmistad> findBySolicitadoId(long idSolicitado);
    List<SolicitaAmistad> findBySolicitanteId(long idSolicitante);
}
