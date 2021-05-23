package it.uniroma3.siw.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
public class Collezione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Curatore curatore;

    @Column(nullable = false)
    private String nome;

    private String descrizione;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Opera> opere;

    public Collezione() {
        this.opere = new ArrayList<>();
    }

    public void eliminaOpera(Opera opera) {
        this.getOpere().remove(opera);
    }

    public void addOpera(Opera opera) {
        this.opere.add(opera);
    }

    public boolean contieneOpera(Opera opera) {
        return this.opere.contains(opera);
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || this.getClass() != o.getClass()) return false;
        return Objects.equals(id, ((Collezione) o).getId());
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
