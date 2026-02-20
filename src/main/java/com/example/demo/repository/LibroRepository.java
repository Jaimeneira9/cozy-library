package com.example.demo.repository;

import com.example.demo.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {

    @Query("SELECT al.libro FROM AutorLibro al WHERE al.autor.nombre = :nombre")
    List<Libro> findByAutorNombre(@Param("nombre") String nombre);

    List<Libro> findByAnioPublicacion(String anioPublicacion);
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
    boolean existsByTituloAndAnioPublicacion(String titulo,String anioPublicacion);
    Optional<Libro> findByGoogleId(String GoogleId);
    Optional<Libro> findById(Long id);

}
