package com.AtleticoBucaramanga.byDanielPerez.club_futbol.repository;

import com.AtleticoBucaramanga.byDanielPerez.club_futbol.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {
    Optional<Jugador> findByNumero(Integer numero);
    List<Jugador> findByPosicion(String posicion);
    List<Jugador> findByNacionalidad(String nacionalidad);
    
    @Query("SELECT j FROM Jugador j ORDER BY j.golesAnotados DESC")
    List<Jugador> findTopScorers();
}