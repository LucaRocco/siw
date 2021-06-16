package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.repository.OperaRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OperaService {
    private final static Logger log = LogManager.getLogger();
    private final OperaRepository operaRepository;

    public OperaService(final OperaRepository operaRepository) {
        this.operaRepository = operaRepository;
    }

    public List<Opera> getOpere() {
        log.debug("Start - getOpere()");
        return this.operaRepository.findAll();
    }

    public Opera findById(final Long idOpera) {
        log.debug("Start - findById({})", idOpera);
        return this.operaRepository.findById(idOpera).orElseThrow(() -> new EntityNotFoundException("Opera not found"));
    }

    public List<Opera> findByCollezione(final Collezione collezione) {
        log.debug("Start - findByCollezione({})", collezione);
        return this.operaRepository.findAllByCollezioniContains(collezione);
    }

    //TODO: Perchè funziona?
    public void eliminaOperaById(final Long idOpera) {
        log.debug("Start - eliminaOperaById({})", idOpera);
        Opera opera = this.findById(idOpera);
        for (Collezione collezione : opera.getCollezioni())
            collezione.eliminaOpera(opera);
        this.operaRepository.deleteById(idOpera);
        log.debug("End - eliminaOperaById({})", idOpera);
    }

    public void salva(final Opera opera, final String posizioneImmagine, final Artista artista) {
        log.debug("Start - salva({}, {}, {})", opera, posizioneImmagine, artista);
        opera.setFoto(posizioneImmagine);
        opera.setAutore(artista);
        this.operaRepository.save(opera);
        log.debug("End - salva({}, {}, {})", opera, posizioneImmagine, artista);
    }
}
