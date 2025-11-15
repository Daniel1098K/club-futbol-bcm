package com.AtleticoBucaramanga.byDanielPerez.club_futbol.controller;

import com.AtleticoBucaramanga.byDanielPerez.club_futbol.model.Club;
import com.AtleticoBucaramanga.byDanielPerez.club_futbol.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clubes")
public class ClubController {
    
    @Autowired
    private ClubService clubService;
    
    @Autowired
    private EntrenadorService entrenadorService;
    
    @Autowired
    private AsociacionService asociacionService;
    
    @Autowired
    private JugadorService jugadorService;
    
    @Autowired
    private CompeticionService competicionService;
    
    @GetMapping
    public String listarClubes(Model model) {
        model.addAttribute("clubes", clubService.findAll());
        return "clubes/lista";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("club", new Club());
        model.addAttribute("entrenadores", entrenadorService.findAll());
        model.addAttribute("asociaciones", asociacionService.findAll());
        return "clubes/formulario";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return clubService.findById(id)
            .map(club -> {
                model.addAttribute("club", club);
                model.addAttribute("entrenadores", entrenadorService.findAll());
                model.addAttribute("asociaciones", asociacionService.findAll());
                return "clubes/formulario";
            })
            .orElseGet(() -> {
                redirectAttributes.addFlashAttribute("error", "Club no encontrado");
                return "redirect:/clubes";
            });
    }
    
    @PostMapping("/guardar")
    public String guardarClub(@Valid @ModelAttribute Club club, BindingResult result, 
                             Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("entrenadores", entrenadorService.findAll());
            model.addAttribute("asociaciones", asociacionService.findAll());
            return "clubes/formulario";
        }
        
        try {
            clubService.save(club);
            redirectAttributes.addFlashAttribute("success", "Club guardado exitosamente");
            return "redirect:/clubes";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el club: " + e.getMessage());
            return "redirect:/clubes";
        }
    }
    
    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return clubService.findById(id)
            .map(club -> {
                model.addAttribute("club", club);
                model.addAttribute("jugadores", club.getJugadores());
                model.addAttribute("competiciones", club.getCompeticiones());
                model.addAttribute("todosJugadores", jugadorService.findAll());
                model.addAttribute("todasCompeticiones", competicionService.findAll());
                return "clubes/detalle";
            })
            .orElseGet(() -> {
                redirectAttributes.addFlashAttribute("error", "Club no encontrado");
                return "redirect:/clubes";
            });
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarClub(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            clubService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Club eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el club: " + e.getMessage());
        }
        return "redirect:/clubes";
    }
    
    @PostMapping("/{clubId}/jugadores/agregar")
    public String agregarJugadorAlClub(@PathVariable Long clubId, @RequestParam Long jugadorId, 
                                       RedirectAttributes redirectAttributes) {
        try {
            clubService.addJugadorToClub(clubId, jugadorId);
            redirectAttributes.addFlashAttribute("success", "Jugador agregado al club exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al agregar jugador: " + e.getMessage());
        }
        return "redirect:/clubes/detalle/" + clubId;
    }
    
    @GetMapping("/{clubId}/jugadores/eliminar/{jugadorId}")
    public String eliminarJugadorDelClub(@PathVariable Long clubId, @PathVariable Long jugadorId, 
                                        RedirectAttributes redirectAttributes) {
        try {
            clubService.removeJugadorFromClub(clubId, jugadorId);
            redirectAttributes.addFlashAttribute("success", "Jugador eliminado del club exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar jugador: " + e.getMessage());
        }
        return "redirect:/clubes/detalle/" + clubId;
    }
    
    @PostMapping("/{clubId}/competiciones/agregar")
    public String agregarCompeticionAlClub(@PathVariable Long clubId, @RequestParam Long competicionId, 
                                          RedirectAttributes redirectAttributes) {
        try {
            clubService.addCompeticionToClub(clubId, competicionId);
            redirectAttributes.addFlashAttribute("success", "Competición agregada al club exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al agregar competición: " + e.getMessage());
        }
        return "redirect:/clubes/detalle/" + clubId;
    }
    
    @GetMapping("/{clubId}/competiciones/eliminar/{competicionId}")
    public String eliminarCompeticionDelClub(@PathVariable Long clubId, @PathVariable Long competicionId, 
                                            RedirectAttributes redirectAttributes) {
        try {
            clubService.removeCompeticionFromClub(clubId, competicionId);
            redirectAttributes.addFlashAttribute("success", "Competición eliminada del club exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar competición: " + e.getMessage());
        }
        return "redirect:/clubes/detalle/" + clubId;
    }
}