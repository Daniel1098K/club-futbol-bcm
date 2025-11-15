package com.AtleticoBucaramanga.byDanielPerez.club_futbol.controller;

import com.AtleticoBucaramanga.byDanielPerez.club_futbol.model.Competicion;
import com.AtleticoBucaramanga.byDanielPerez.club_futbol.service.CompeticionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/competiciones")
public class CompeticionController {
    
    @Autowired
    private CompeticionService competicionService;
    
    @GetMapping
    public String listarCompeticiones(Model model) {
        model.addAttribute("competiciones", competicionService.findAll());
        return "competiciones/lista";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("competicion", new Competicion());
        return "competiciones/formulario";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return competicionService.findById(id)
            .map(competicion -> {
                model.addAttribute("competicion", competicion);
                return "competiciones/formulario";
            })
            .orElseGet(() -> {
                redirectAttributes.addFlashAttribute("error", "Competición no encontrada");
                return "redirect:/competiciones";
            });
    }
    
    @PostMapping("/guardar")
    public String guardarCompeticion(@Valid @ModelAttribute Competicion competicion, BindingResult result, 
                                    Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "competiciones/formulario";
        }
        
        try {
            competicionService.save(competicion);
            redirectAttributes.addFlashAttribute("success", "Competición guardada exitosamente");
            return "redirect:/competiciones";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar la competición: " + e.getMessage());
            return "redirect:/competiciones";
        }
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarCompeticion(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            competicionService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Competición eliminada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la competición: " + e.getMessage());
        }
        return "redirect:/competiciones";
    }
}