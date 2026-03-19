package com.example.demo.repository;

import com.example.demo.model.Resenia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReseniaRepository extends JpaRepository<Resenia,Long> {
    List<Resenia> findByUsuarioIdInOrderByFechaDesc(List<Long> amigosIds);

    // Obtener todas las reseñas de un libro específico
    List<Resenia> findByLibroId(Long libroId);



}
