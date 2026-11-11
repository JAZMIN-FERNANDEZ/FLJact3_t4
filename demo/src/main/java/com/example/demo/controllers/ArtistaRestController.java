package com.example.demo.controllers;

import com.example.demo.models.Artista;
import com.example.demo.service.ArtistaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/artistas")
public class ArtistaRestController {

    private final ArtistaService artistaService;

    public ArtistaRestController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @GetMapping
    public List<Artista> listar() {
        return artistaService.listar();
    }

    @PostMapping
    public ResponseEntity<Artista> crear(@RequestBody Artista artista) {
        return ResponseEntity.status(HttpStatus.CREATED).body(artistaService.crear(artista));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> invalido(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }
}