package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Curatore;
import it.uniroma3.siw.repository.CuratoreRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CuratoreService {
    private final CuratoreRepository curatoreRepository;

    public CuratoreService(CuratoreRepository curatoreRepository) {
        this.curatoreRepository = curatoreRepository;
    }

    public List<Curatore> getCuratori() {
        return this.curatoreRepository.findAll();
    }

    public Curatore findById(String matricola) {
        return this.curatoreRepository.findById(matricola).orElseThrow(() -> new EntityNotFoundException("Curatore non trovato"));
    }
}
