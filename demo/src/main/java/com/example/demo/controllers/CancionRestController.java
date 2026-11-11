package com.example.demo.controllers;

import com.example.demo.models.Cancion;
import com.example.demo.service.CancionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/canciones")
public class CancionRestController {

    private final CancionService cancionService;

    public CancionRestController(CancionService cancionService) {
        this.cancionService = cancionService;
    }

    @GetMapping
    public List<Cancion> listar() {
        return cancionService.listar();
    }

    @GetMapping("/{id}")
    public Cancion obtener(@PathVariable Integer id) {
        return cancionService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Cancion> crear(@RequestBody Cancion cancion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cancionService.crear(cancion));
    }

    @PutMapping("/{id}")
    public Cancion actualizar(@PathVariable Integer id, @RequestBody Cancion cancion) {
        return cancionService.actualizar(id, cancion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        cancionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> noEncontrada(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> invalida(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }
}