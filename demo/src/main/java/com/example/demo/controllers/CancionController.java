package com.example.demo.controllers;

import com.example.demo.models.Artista;
import com.example.demo.models.Cancion;
import com.example.demo.service.ArtistaService;
import com.example.demo.service.CancionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/canciones")
public class CancionController {

    private final CancionService cancionService;
    private final ArtistaService artistaService;

    public CancionController(CancionService cancionService, ArtistaService artistaService) {
        this.cancionService = cancionService;
        this.artistaService = artistaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("canciones", cancionService.listar());
        return "canciones";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Cancion cancion = new Cancion();
        cancion.setArtista(new Artista());
        model.addAttribute("cancion", cancion);
        model.addAttribute("artistas", artistaService.listar());
        return "cancion-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cancion cancion, Model model) {
        try {
            if (cancion.getId() == null) {
                cancionService.crear(cancion);
            } else {
                cancionService.actualizar(cancion.getId(), cancion);
            }
        } catch (IllegalArgumentException | NoSuchElementException e) {
            if (cancion.getArtista() == null) {
                cancion.setArtista(new Artista());
            }
            model.addAttribute("error", e.getMessage());
            model.addAttribute("artistas", artistaService.listar());
            return "cancion-form";
        }
        return "redirect:/canciones";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("cancion", cancionService.buscarPorId(id));
        model.addAttribute("artistas", artistaService.listar());
        return "cancion-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        cancionService.eliminar(id);
        return "redirect:/canciones";
    }
}