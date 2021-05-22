package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.repository.CollezioneRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CollezioneService {
    private final CollezioneRepository collezioneRepository;

    public CollezioneService(CollezioneRepository collezioneRepository) {
        this.collezioneRepository = collezioneRepository;
    }

    public List<Collezione> getCollezioni() {
        return this.collezioneRepository.findAll();
    }

    public Collezione getById(Long idCollezione) {
        return collezioneRepository.findById(idCollezione).orElse(null);
    }

    public Collezione salvaCollezione(Collezione collezione) {
        return this.collezioneRepository.save(collezione);
    }

    public Collezione aggiornaCollezione(Collezione collezioneCorrente) {
        Collezione collezione = this.collezioneRepository.
                findById(collezioneCorrente.getId()).orElseThrow(() -> new EntityNotFoundException("Collezione non trovata"));
        if(collezioneCorrente.getOpere() != null) {
            collezione.setOpere(collezioneCorrente.getOpere());
        }
        if(collezioneCorrente.getCuratore() != null) {
            collezione.setCuratore(collezioneCorrente.getCuratore());
        }
        collezione.setNome(collezioneCorrente.getNome());
        collezione.setDescrizione(collezioneCorrente.getDescrizione());
        return this.collezioneRepository.save(collezione);
    }
}
