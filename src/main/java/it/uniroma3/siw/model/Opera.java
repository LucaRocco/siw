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

    @ManyToMany(mappedBy = "opere")
    private List<Collezione> collezioni;

    @ManyToOne
    @JoinColumn(name = "autore_id")
    private Artista autore;

    @Override
    public boolean equals(Object o) {
        if(o == null || o.getClass() != this.getClass()) return false;
        return this.id.equals(((Opera)o).getId());
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    public void eliminaCollezione(Collezione collezione) {
        this.collezioni.remove(collezione);
    }
}
