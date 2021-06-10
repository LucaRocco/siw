package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Curatore;
import it.uniroma3.siw.repository.CuratoreRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CuratoreService {
    private static final Logger log = LogManager.getLogger();
    private final CuratoreRepository curatoreRepository;

    public CuratoreService(final CuratoreRepository curatoreRepository) {
        this.curatoreRepository = curatoreRepository;
    }

    public List<Curatore> getCuratori() {
        log.debug("Start - getCuratori()");
        return this.curatoreRepository.findAll();
    }

    public Curatore findById(final String matricola) {
        log.debug("Start - findById({})", matricola);
        return this.curatoreRepository.findById(matricola).orElseThrow(() -> new EntityNotFoundException("Curatore non trovato"));
    }
}
