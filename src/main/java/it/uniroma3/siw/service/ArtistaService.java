package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.repository.ArtistaRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ArtistaService {
    private static final Logger log = LogManager.getLogger();
    private final ArtistaRepository artistaRepository;

    public ArtistaService(final ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public List<Artista> getArtisti() {
        log.debug("Start - getArtisti()");
        return this.artistaRepository.findAll();
    }

    public Artista findArtistaById(final Long idArtista) {
        log.debug("Start - findArtistaById({})", idArtista);
        return this.artistaRepository.findById(idArtista).orElseThrow(() -> new EntityNotFoundException("Artista non trovato"));
    }
}
