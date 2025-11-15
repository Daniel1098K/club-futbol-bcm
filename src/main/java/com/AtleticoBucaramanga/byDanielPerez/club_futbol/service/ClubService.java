package com.AtleticoBucaramanga.byDanielPerez.club_futbol.service;

import com.AtleticoBucaramanga.byDanielPerez.club_futbol.model.*;
import com.AtleticoBucaramanga.byDanielPerez.club_futbol.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClubService {
    
    @Autowired
    private ClubRepository clubRepository;
    
    @Autowired
    private JugadorRepository jugadorRepository;
    
    @Autowired
    private CompeticionRepository competicionRepository;
    
    public List<Club> findAll() {
        return clubRepository.findAll();
    }
    
    public Optional<Club> findById(Long id) {
        return clubRepository.findById(id);
    }
    
    public Optional<Club> findByIdWithJugadores(Long id) {
        return clubRepository.findByIdWithJugadores(id);
    }
    
    public Optional<Club> findByIdWithCompeticiones(Long id) {
        return clubRepository.findByIdWithCompeticiones(id);
    }
    
    public Club save(Club club) {
        return clubRepository.save(club);
    }
    
    public void deleteById(Long id) {
        clubRepository.deleteById(id);
    }
    
    public Club addJugadorToClub(Long clubId, Long jugadorId) {
        Optional<Club> clubOpt = clubRepository.findById(clubId);
        Optional<Jugador> jugadorOpt = jugadorRepository.findById(jugadorId);
        
        if (clubOpt.isPresent() && jugadorOpt.isPresent()) {
            Club club = clubOpt.get();
            Jugador jugador = jugadorOpt.get();
            club.addJugador(jugador);
            return clubRepository.save(club);
        }
        throw new RuntimeException("Club o Jugador no encontrado");
    }
    
    public Club removeJugadorFromClub(Long clubId, Long jugadorId) {
        Optional<Club> clubOpt = clubRepository.findByIdWithJugadores(clubId);
        Optional<Jugador> jugadorOpt = jugadorRepository.findById(jugadorId);
        
        if (clubOpt.isPresent() && jugadorOpt.isPresent()) {
            Club club = clubOpt.get();
            Jugador jugador = jugadorOpt.get();
            club.removeJugador(jugador);
            jugadorRepository.delete(jugador);
            return clubRepository.save(club);
        }
        throw new RuntimeException("Club o Jugador no encontrado");
    }
    
    public Club addCompeticionToClub(Long clubId, Long competicionId) {
        Optional<Club> clubOpt = clubRepository.findById(clubId);
        Optional<Competicion> competicionOpt = competicionRepository.findById(competicionId);
        
        if (clubOpt.isPresent() && competicionOpt.isPresent()) {
            Club club = clubOpt.get();
            Competicion competicion = competicionOpt.get();
            club.addCompeticion(competicion);
            return clubRepository.save(club);
        }
        throw new RuntimeException("Club o Competición no encontrada");
    }
    
    public Club removeCompeticionFromClub(Long clubId, Long competicionId) {
        Optional<Club> clubOpt = clubRepository.findByIdWithCompeticiones(clubId);
        Optional<Competicion> competicionOpt = competicionRepository.findById(competicionId);
        
        if (clubOpt.isPresent() && competicionOpt.isPresent()) {
            Club club = clubOpt.get();
            Competicion competicion = competicionOpt.get();
            club.removeCompeticion(competicion);
            return clubRepository.save(club);
        }
        throw new RuntimeException("Club o Competición no encontrada");
    }
}