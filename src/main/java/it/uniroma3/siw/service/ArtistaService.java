package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.repository.ArtistaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ArtistaService {
    private final ArtistaRepository artistaRepository;

    public ArtistaService(final ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public List<Artista> getArtisti() {
        return this.artistaRepository.findAll();
    }

    public Artista findArtistaById(Long idArtista) {
        return this.artistaRepository.findById(idArtista).orElseThrow(() -> new EntityNotFoundException("Artista non trovato"));
    }
}
