package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Opera;
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

    public Collezione findById(Long idCollezione) {
        return collezioneRepository.findById(idCollezione).orElse(null);
    }

    public Collezione salvaCollezione(Collezione collezione) {
        return this.collezioneRepository.save(collezione);
    }

    public void aggiornaCollezione(Long idCollezione, Collezione collezioneCorrente) {
        Collezione collezione = this.collezioneRepository.
                findById(idCollezione).orElseThrow(() -> new EntityNotFoundException("Collezione non trovata"));
        collezione.setNome(collezioneCorrente.getNome());
        collezione.setDescrizione(collezioneCorrente.getDescrizione());
        this.collezioneRepository.save(collezione);
    }

    public void eliminaOpera(Long idCollezione, Opera opera) {
        Collezione collezione = this.findById(idCollezione);
        collezione.eliminaOpera(opera);
        this.collezioneRepository.save(collezione);
    }

    public void aggiungiOpera(Long idCollezione, Opera opera) {
        Collezione collezione = this.findById(idCollezione);
        collezione.addOpera(opera);
        this.collezioneRepository.save(collezione);
    }

}
