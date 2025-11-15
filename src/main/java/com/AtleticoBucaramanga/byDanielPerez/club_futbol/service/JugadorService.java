package com.AtleticoBucaramanga.byDanielPerez.club_futbol.service;

import com.AtleticoBucaramanga.byDanielPerez.club_futbol.model.Jugador;
import com.AtleticoBucaramanga.byDanielPerez.club_futbol.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JugadorService {
    
    @Autowired
    private JugadorRepository jugadorRepository;
    
    public List<Jugador> findAll() {
        return jugadorRepository.findAll();
    }
    
    public Optional<Jugador> findById(Long id) {
        return jugadorRepository.findById(id);
    }
    
    public Jugador save(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }
    
    public void deleteById(Long id) {
        jugadorRepository.deleteById(id);
    }
    
    public List<Jugador> findByPosicion(String posicion) {
        return jugadorRepository.findByPosicion(posicion);
    }
    
    public List<Jugador> findTopScorers() {
        return jugadorRepository.findTopScorers();
    }
}
