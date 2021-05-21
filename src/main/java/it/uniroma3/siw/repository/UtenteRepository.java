package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Amministratore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Amministratore, Long> {
}
