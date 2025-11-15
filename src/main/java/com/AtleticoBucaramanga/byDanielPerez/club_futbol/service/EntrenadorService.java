package com.AtleticoBucaramanga.byDanielPerez.club_futbol.service;

import com.AtleticoBucaramanga.byDanielPerez.club_futbol.model.Entrenador;
import com.AtleticoBucaramanga.byDanielPerez.club_futbol.repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EntrenadorService {
    
    @Autowired
    private EntrenadorRepository entrenadorRepository;
    
    public List<Entrenador> findAll() {
        return entrenadorRepository.findAll();
    }
    
    public Optional<Entrenador> findById(Long id) {
        return entrenadorRepository.findById(id);
    }
    
    public Entrenador save(Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }
    
    public void deleteById(Long id) {
        entrenadorRepository.deleteById(id);
    }
    
    public List<Entrenador> findByNacionalidad(String nacionalidad) {
        return entrenadorRepository.findByNacionalidad(nacionalidad);
    }
}