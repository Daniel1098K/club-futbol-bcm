package com.AtleticoBucaramanga.byDanielPerez.club_futbol.repository;

import com.AtleticoBucaramanga.byDanielPerez.club_futbol.model.Competicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CompeticionRepository extends JpaRepository<Competicion, Long> {
    List<Competicion> findByCategoria(String categoria);
    
    @Query("SELECT c FROM Competicion c WHERE c.fechaInicio <= :fecha AND c.fechaFin >= :fecha")
    List<Competicion> findCompeticionesActivas(LocalDate fecha);
    
    List<Competicion> findByFechaInicioAfter(LocalDate fecha);
}