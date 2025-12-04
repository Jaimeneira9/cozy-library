package com.example.demo.repository;

import com.example.demo.model.Usuario;
import com.example.demo.model.compositePK.SolicitaAmistad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitaAmistadRepository extends JpaRepository<SolicitaAmistad,Long> {
    Usuario findBySolicitanteId(long id);
    Usuario findBySolicitadoId(long id);
}
