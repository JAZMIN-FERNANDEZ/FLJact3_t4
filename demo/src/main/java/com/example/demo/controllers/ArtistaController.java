package com.example.demo.controllers;

import com.example.demo.models.Artista;
import com.example.demo.service.ArtistaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/artistas")
public class ArtistaController {

    private final ArtistaService artistaService;

    public ArtistaController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("artistas", artistaService.listar());
        model.addAttribute("artista", new Artista());
        return "artistas";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Artista artista, Model model) {
        try {
            artistaService.crear(artista);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("artistas", artistaService.listar());
            return "artistas";
        }
        return "redirect:/artistas";
    }
}