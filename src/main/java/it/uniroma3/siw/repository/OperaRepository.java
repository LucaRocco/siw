package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Opera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperaRepository extends JpaRepository<Opera, Long> {
    List<Opera> findAllByCollezioniContains(final Collezione collezione);
    void deleteById(final Long idOpera);
}
