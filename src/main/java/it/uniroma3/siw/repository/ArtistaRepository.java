package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
}
