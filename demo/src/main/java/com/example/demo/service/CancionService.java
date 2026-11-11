package com.example.demo.service;

import com.example.demo.models.Artista;
import com.example.demo.models.Cancion;
import com.example.demo.repository.ArtistaRepository;
import com.example.demo.repository.CancionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CancionService {

    private final CancionRepository cancionRepo;
    private final ArtistaRepository artistaRepo;

    public CancionService(CancionRepository cancionRepo, ArtistaRepository artistaRepo) {
        this.cancionRepo = cancionRepo;
        this.artistaRepo = artistaRepo;
    }

    public List<Cancion> listar() {
        return cancionRepo.findAll();
    }

    public Cancion buscarPorId(Integer id) {
        return cancionRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Canción no encontrada: " + id));
    }

    public Cancion crear(Cancion cancion) {
        validar(cancion);
        cancion.setId(null);
        return cancionRepo.save(cancion);
    }

    public Cancion actualizar(Integer id, Cancion datos) {
        Cancion existente = buscarPorId(id);
        validar(datos);
        existente.setTitulo(datos.getTitulo());
        existente.setAnio(datos.getAnio());
        existente.setGenero(datos.getGenero());
        existente.setArtista(datos.getArtista());
        return cancionRepo.save(existente);
    }

    public void eliminar(Integer id) {
        if (!cancionRepo.existsById(id)) {
            throw new NoSuchElementException("Canción no encontrada: " + id);
        }
        cancionRepo.deleteById(id);
    }

    // Reglas de negocio: título obligatorio, año válido y artista existente
    private void validar(Cancion cancion) {
        if (cancion.getTitulo() == null || cancion.getTitulo().isBlank()) {
            throw new IllegalArgumentException("El título es obligatorio");
        }
        if (cancion.getAnio() != null && cancion.getAnio() > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("El año no puede ser futuro");
        }
        if (cancion.getArtista() == null || cancion.getArtista().getId() == null) {
            throw new IllegalArgumentException("Debes seleccionar un artista");
        }
        Artista artista = artistaRepo.findById(cancion.getArtista().getId())
                .orElseThrow(() -> new IllegalArgumentException("El artista no existe"));
        cancion.setArtista(artista);
    }
}