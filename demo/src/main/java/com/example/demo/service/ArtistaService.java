package com.example.demo.service;

import com.example.demo.models.Artista;
import com.example.demo.repository.ArtistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistaService {

    private final ArtistaRepository artistaRepo;

    public ArtistaService(ArtistaRepository artistaRepo) {
        this.artistaRepo = artistaRepo;
    }

    public List<Artista> listar() {
        return artistaRepo.findAll();
    }

    public Artista crear(Artista artista) {
        if (artista.getNombre() == null || artista.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del artista es obligatorio");
        }
        artista.setId(null);
        return artistaRepo.save(artista);
    }
}