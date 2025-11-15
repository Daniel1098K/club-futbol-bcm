package com.AtleticoBucaramanga.byDanielPerez.club_futbol.repository;

import com.AtleticoBucaramanga.byDanielPerez.club_futbol.model.Asociacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AsociacionRepository extends JpaRepository<Asociacion, Long> {
    Optional<Asociacion> findByNombre(String nombre);
    List<Asociacion> findByPais(String pais);
}