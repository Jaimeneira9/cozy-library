package com.example.demo.repository;

import com.example.demo.model.Resenia;
import com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReseniaRepository extends JpaRepository<Resenia,Long> {
    Optional<List<Resenia>> findByUsuarioId(long id);
    Optional<List<Resenia>> findByLibroId(long id);
    void deleteByUsuarioId(long id);
    void deleteByLibroId(long id);
}
