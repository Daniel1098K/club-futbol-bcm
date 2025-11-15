package com.AtleticoBucaramanga.byDanielPerez.club_futbol.repository;

import com.AtleticoBucaramanga.byDanielPerez.club_futbol.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByNombre(String nombre);
    List<Club> findByCiudad(String ciudad);
    
    @Query("SELECT c FROM Club c LEFT JOIN FETCH c.jugadores WHERE c.id = :id")
    Optional<Club> findByIdWithJugadores(Long id);
    
    @Query("SELECT c FROM Club c LEFT JOIN FETCH c.competiciones WHERE c.id = :id")
    Optional<Club> findByIdWithCompeticiones(Long id);
}