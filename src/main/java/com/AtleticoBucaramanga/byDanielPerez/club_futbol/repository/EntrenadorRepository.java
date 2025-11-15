package com.AtleticoBucaramanga.byDanielPerez.club_futbol.repository;

import com.AtleticoBucaramanga.byDanielPerez.club_futbol.model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {
    List<Entrenador> findByNacionalidad(String nacionalidad);
    List<Entrenador> findByEdadBetween(Integer minEdad, Integer maxEdad);
}
