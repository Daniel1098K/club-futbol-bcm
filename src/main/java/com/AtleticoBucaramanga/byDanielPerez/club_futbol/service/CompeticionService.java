package com.AtleticoBucaramanga.byDanielPerez.club_futbol.service;

import com.AtleticoBucaramanga.byDanielPerez.club_futbol.model.Competicion;
import com.AtleticoBucaramanga.byDanielPerez.club_futbol.repository.CompeticionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompeticionService {
    
    @Autowired
    private CompeticionRepository competicionRepository;
    
    public List<Competicion> findAll() {
        return competicionRepository.findAll();
    }
    
    public Optional<Competicion> findById(Long id) {
        return competicionRepository.findById(id);
    }
    
    public Competicion save(Competicion competicion) {
        return competicionRepository.save(competicion);
    }
    
    public void deleteById(Long id) {
        competicionRepository.deleteById(id);
    }
    
    public List<Competicion> findCompeticionesActivas() {
        return competicionRepository.findCompeticionesActivas(LocalDate.now());
    }
}