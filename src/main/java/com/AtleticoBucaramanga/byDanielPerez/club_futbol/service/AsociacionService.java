package com.AtleticoBucaramanga.byDanielPerez.club_futbol.service;

import com.AtleticoBucaramanga.byDanielPerez.club_futbol.model.Asociacion;
import com.AtleticoBucaramanga.byDanielPerez.club_futbol.repository.AsociacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AsociacionService {
    
    @Autowired
    private AsociacionRepository asociacionRepository;
    
    public List<Asociacion> findAll() {
        return asociacionRepository.findAll();
    }
    
    public Optional<Asociacion> findById(Long id) {
        return asociacionRepository.findById(id);
    }
    
    public Asociacion save(Asociacion asociacion) {
        return asociacionRepository.save(asociacion);
    }
    
    public void deleteById(Long id) {
        asociacionRepository.deleteById(id);
    }
}