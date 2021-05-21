package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.repository.ArtistaRepository;

import java.util.List;

public class ArtistaService {
    private final ArtistaRepository artistaRepository;

    public ArtistaService(final ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public List<Artista> getArtisti() {
        return this.artistaRepository.findAll();
    }
}
