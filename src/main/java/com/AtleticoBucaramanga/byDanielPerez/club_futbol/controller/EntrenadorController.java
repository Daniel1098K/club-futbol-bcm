	package com.AtleticoBucaramanga.byDanielPerez.club_futbol.controller;

import com.AtleticoBucaramanga.byDanielPerez.club_futbol.model.Entrenador;
import com.AtleticoBucaramanga.byDanielPerez.club_futbol.service.EntrenadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/entrenadores")
public class EntrenadorController {
    
    @Autowired
    private EntrenadorService entrenadorService;
    
    @GetMapping
    public String listarEntrenadores(Model model) {
        model.addAttribute("entrenadores", entrenadorService.findAll());
        return "entrenadores/lista";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        return "entrenadores/formulario";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return entrenadorService.findById(id)
            .map(entrenador -> {
                model.addAttribute("entrenador", entrenador);
                return "entrenadores/formulario";
            })
            .orElseGet(() -> {
                redirectAttributes.addFlashAttribute("error", "Entrenador no encontrado");
                return "redirect:/entrenadores";
            });
    }
    
    @PostMapping("/guardar")
    public String guardarEntrenador(@Valid @ModelAttribute Entrenador entrenador, BindingResult result, 
                                   Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "entrenadores/formulario";
        }
        
        try {
            entrenadorService.save(entrenador);
            redirectAttributes.addFlashAttribute("success", "Entrenador guardado exitosamente");
            return "redirect:/entrenadores";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el entrenador: " + e.getMessage());
            return "redirect:/entrenadores";
        }
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarEntrenador(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            entrenadorService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Entrenador eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el entrenador: " + e.getMessage());
        }
        return "redirect:/entrenadores";
    }
}