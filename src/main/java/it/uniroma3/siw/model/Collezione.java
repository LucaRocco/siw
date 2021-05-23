package it.uniroma3.siw.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
public class Collezione {
    @Id
    private Long id;

    @ManyToOne
    private Curatore curatore;

    @Column(nullable = false)
    private String nome;

    private String descrizione;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Opera> opere;

    public void eliminaOpera(Opera opera) {
        this.getOpere().remove(opera);
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
