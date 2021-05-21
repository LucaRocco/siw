package it.uniroma3.siw.model;

import lombok.*;

import javax.persistence.*;
import java.time.Year;
import java.util.List;

@Data
@Entity
public class Opera {

    @Id
    private Long id;

    @Column(nullable = false)
    private String titolo;

    private Year annoRealizzazione;

    private String descrizione;

    @ManyToMany
    private List<Collezione> collezioni;

    @ManyToOne
    @JoinColumn(name = "autore_id")
    private Artista autore;
}
