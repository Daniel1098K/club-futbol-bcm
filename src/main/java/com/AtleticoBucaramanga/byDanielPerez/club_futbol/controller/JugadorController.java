package com.AtleticoBucaramanga.byDanielPerez.club_futbol.controller;

import com.AtleticoBucaramanga.byDanielPerez.club_futbol.model.Jugador;
import com.AtleticoBucaramanga.byDanielPerez.club_futbol.service.JugadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/jugadores")
public class JugadorController {
    
    @Autowired
    private JugadorService jugadorService;
    
    @GetMapping
    public String listarJugadores(Model model) {
        model.addAttribute("jugadores", jugadorService.findAll());
        return "jugadores/lista";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("jugador", new Jugador());
        return "jugadores/formulario";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return jugadorService.findById(id)
            .map(jugador -> {
                model.addAttribute("jugador", jugador);
                return "jugadores/formulario";
            })
            .orElseGet(() -> {
                redirectAttributes.addFlashAttribute("error", "Jugador no encontrado");
                return "redirect:/jugadores";
            });
    }
    
    @PostMapping("/guardar")
    public String guardarJugador(@Valid @ModelAttribute Jugador jugador, BindingResult result, 
                                Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "jugadores/formulario";
        }
        
        try {
            jugadorService.save(jugador);
            redirectAttributes.addFlashAttribute("success", "Jugador guardado exitosamente");
            return "redirect:/jugadores";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el jugador: " + e.getMessage());
            return "redirect:/jugadores";
        }
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarJugador(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            jugadorService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Jugador eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el jugador: " + e.getMessage());
        }
        return "redirect:/jugadores";
    }
}