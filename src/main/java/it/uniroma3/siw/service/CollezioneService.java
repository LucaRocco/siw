package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Curatore;
import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.repository.CollezioneRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CollezioneService {
    private static final Logger log = LogManager.getLogger();
    private final CollezioneRepository collezioneRepository;

    public CollezioneService(final CollezioneRepository collezioneRepository) {
        this.collezioneRepository = collezioneRepository;
    }

    public List<Collezione> getCollezioni() {
        log.debug("Start - getCollezioni()");
        return this.collezioneRepository.findAll();
    }

    public Collezione findById(final Long idCollezione) {
        log.debug("Start - findById({})", idCollezione);
        return collezioneRepository.findById(idCollezione).orElse(null);
    }

    public void salvaCollezione(final Collezione collezione, final Curatore curatore) {
        log.debug("Start - findById({}, {})", collezione, curatore);
        collezione.setCuratore(curatore);
        this.collezioneRepository.save(collezione);
        log.debug("End - findById({}, {})", collezione, curatore);
    }

    public void aggiornaCollezione(final Long idCollezione, final Collezione collezioneAggiornata) {
        log.debug("Start - aggiornaCollezione({}, {})", idCollezione, collezioneAggiornata);
        Collezione collezione = this.collezioneRepository.
                findById(idCollezione).orElseThrow(() -> new EntityNotFoundException("Collezione non trovata"));
        collezione.setNome(collezioneAggiornata.getNome());
        collezione.setDescrizione(collezioneAggiornata.getDescrizione());
        this.collezioneRepository.save(collezione);
        log.debug("End - aggiornaCollezione({}, {})", idCollezione, collezioneAggiornata);
    }

    public void eliminaOpera(final Long idCollezione, final Opera opera) {
        log.debug("Start - eliminaOpera({}, {})", idCollezione, opera);
        Collezione collezione = this.findById(idCollezione);
        collezione.eliminaOpera(opera);
        this.collezioneRepository.save(collezione);
        log.debug("End - eliminaOpera({}, {})", idCollezione, opera);
    }

    public void aggiungiOpera(final Long idCollezione, final Opera opera) {
        log.debug("Start - aggiungiOpera({}, {})", idCollezione, opera);
        Collezione collezione = this.findById(idCollezione);
        collezione.addOpera(opera);
        this.collezioneRepository.save(collezione);
        log.debug("End - aggiungiOpera({}, {})", idCollezione, opera);
    }

    public void eliminaCollezioneById(final Long idCollezione) {
        log.debug("Start - eliminaCollezioneById({})", idCollezione);
        this.collezioneRepository.deleteById(idCollezione);
        log.debug("End - eliminaCollezioneById({})", idCollezione);
    }
}
