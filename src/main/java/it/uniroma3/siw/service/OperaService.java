package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.repository.OperaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OperaService {
    private final OperaRepository operaRepository;

    public OperaService(OperaRepository operaRepository) {
        this.operaRepository = operaRepository;
    }

    public List<Opera> getOpere() {
        return this.operaRepository.findAll();
    }

    public Opera findById(Long idOpera) {
        return this.operaRepository.findById(idOpera).orElseThrow(() -> new EntityNotFoundException("Opera not found"));
    }

    public List<Opera> findByCollezione(Collezione collezione) {
        return this.operaRepository.findAllByCollezioniContains(collezione);
    }

    //TODO: Perchè funziona?
    public void eliminaOperaById(Long idOpera) {
        Opera opera = this.findById(idOpera);
        for(Collezione collezione : opera.getCollezioni())
            collezione.eliminaOpera(opera);
        this.operaRepository.deleteById(idOpera);
    }

    public void salva(Opera opera, String posizioneImmagine, Artista artista) {
        opera.setFoto(posizioneImmagine);
        opera.setAutore(artista);
        this.operaRepository.save(opera);
    }
}
