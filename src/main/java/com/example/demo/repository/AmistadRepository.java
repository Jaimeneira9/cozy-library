package com.example.demo.repository;

import com.example.demo.model.Amistad;

import com.example.demo.model.compositePK.AmistadId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AmistadRepository extends JpaRepository <Amistad, AmistadId> {
    List<Amistad> findByUsuario1Id(long id);
    List<Amistad> findByUsuario2Id(long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Amistad a " +
            "WHERE (a.usuario1.id = :id1 AND a.usuario2.id = :id2) " +
            "   OR (a.usuario1.id = :id2 AND a.usuario2.id = :id1)")
    void eliminarAmistad(Long id1, Long id2);
}
