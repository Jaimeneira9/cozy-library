package com.example.demo.repository;

import com.example.demo.model.Lectura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LecturaRepository extends JpaRepository<Lectura,Long> {
    // Suma todas las páginas leídas por un usuario (lo que vimos en el diseño)
    @Query("SELECT SUM(l.paginas_actuales) FROM Lectura l WHERE l.usuario.id = :usuarioId")
    Long sumarTotalPaginasLeidas(@Param("usuarioId") Long usuarioId);

    // Cuenta cuántos libros ha terminado (para la medalla de "Libros leídos")
    @Query("SELECT COUNT(l) FROM Lectura l WHERE l.usuario.id = :usuarioId AND l.estado = 'TERMINADO'")
    Long contarLibrosTerminados(@Param("usuarioId") Long usuarioId);

    // Calcula la valoración media de sus lecturas
    @Query("SELECT AVG(l.valoracion) FROM Lectura l WHERE l.usuario.id = :usuarioId AND l.valoracion IS NOT NULL")
    Double obtenerRatingPromedio(@Param("usuarioId") Long usuarioId);

    Optional<Lectura> findByUsuarioIdAndLibroId(long idUsuario,long idLibro);

}
