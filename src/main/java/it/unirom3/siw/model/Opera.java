package it.unirom3.siw.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;

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
    private Artista autore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Year getAnnoRealizzazione() {
        return annoRealizzazione;
    }

    public void setAnnoRealizzazione(Year annoRealizzazione) {
        this.annoRealizzazione = annoRealizzazione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Collezione> getCollezioni() {
        return collezioni;
    }

    public void setCollezioni(List<Collezione> collezioni) {
        this.collezioni = collezioni;
    }

    public Artista getAutore() {
        return autore;
    }

    public void setAutore(Artista autore) {
        this.autore = autore;
    }
}
