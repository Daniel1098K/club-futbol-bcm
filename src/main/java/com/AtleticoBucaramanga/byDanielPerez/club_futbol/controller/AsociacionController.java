package com.AtleticoBucaramanga.byDanielPerez.club_futbol.controller;

import com.AtleticoBucaramanga.byDanielPerez.club_futbol.model.Asociacion;
import com.AtleticoBucaramanga.byDanielPerez.club_futbol.service.AsociacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/asociaciones")
public class AsociacionController {
    
    @Autowired
    private AsociacionService asociacionService;
    
    @GetMapping
    public String listarAsociaciones(Model model) {
        model.addAttribute("asociaciones", asociacionService.findAll());
        return "asociaciones/lista";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("asociacion", new Asociacion());
        return "asociaciones/formulario";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return asociacionService.findById(id)
            .map(asociacion -> {
                model.addAttribute("asociacion", asociacion);
                return "asociaciones/formulario";
            })
            .orElseGet(() -> {
                redirectAttributes.addFlashAttribute("error", "Asociación no encontrada");
                return "redirect:/asociaciones";
            });
    }
    
    @PostMapping("/guardar")
    public String guardarAsociacion(@Valid @ModelAttribute Asociacion asociacion, BindingResult result, 
                                   Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "asociaciones/formulario";
        }
        
        try {
            asociacionService.save(asociacion);
            redirectAttributes.addFlashAttribute("success", "Asociación guardada exitosamente");
            return "redirect:/asociaciones";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar la asociación: " + e.getMessage());
            return "redirect:/asociaciones";
        }
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarAsociacion(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            asociacionService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Asociación eliminada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la asociación: " + e.getMessage());
        }
        return "redirect:/asociaciones";
    }
}