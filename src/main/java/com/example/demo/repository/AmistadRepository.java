package com.example.demo.repository;

import com.example.demo.model.Amistad;
import com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AmistadRepository extends JpaRepository <Amistad,Long> {
    Optional<Amistad> findByUsuario1Id(long id);
    Optional<Amistad> findByUsuario2Id(long id);
}
