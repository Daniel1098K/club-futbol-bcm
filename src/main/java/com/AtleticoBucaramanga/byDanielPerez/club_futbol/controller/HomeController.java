package com.AtleticoBucaramanga.byDanielPerez.club_futbol.controller;

import com.AtleticoBucaramanga.byDanielPerez.club_futbol.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @Autowired
    private ClubService clubService;
    
    @Autowired
    private JugadorService jugadorService;
    
    @Autowired
    private EntrenadorService entrenadorService;
    
    @Autowired
    private AsociacionService asociacionService;
    
    @Autowired
    private CompeticionService competicionService;
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("totalClubes", clubService.findAll().size());
        model.addAttribute("totalJugadores", jugadorService.findAll().size());
        model.addAttribute("totalEntrenadores", entrenadorService.findAll().size());
        model.addAttribute("totalCompeticiones", competicionService.findAll().size());
        return "index";
    }
}