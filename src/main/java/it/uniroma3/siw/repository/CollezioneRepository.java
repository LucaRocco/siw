package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Collezione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollezioneRepository extends JpaRepository<Collezione, Long> {
    void deleteById(final Long idCollezione);
}
