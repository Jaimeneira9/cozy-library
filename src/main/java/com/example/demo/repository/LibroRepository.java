package com.example.demo.repository;

import com.example.demo.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {

    List<Libro> findByAnioPublicacion(int anioPublicacion);
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
    Optional<Libro> findById(Long id);

}
