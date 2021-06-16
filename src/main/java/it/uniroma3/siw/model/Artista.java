package it.uniroma3.siw.model;

import it.uniroma3.siw.model.base.Persona;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Artista extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String foto;

    private String luogoDiMorte;

    private Date dataDiMorte;

    private String nazionalita;

    private String biografia;

    @OneToMany(mappedBy = "autore", fetch = FetchType.EAGER)
    private List<Opera> opere;

    public Artista(String nome, String cognome) {
        super(nome, cognome);
    }

    @Override
    public String toString() {
        return "Artista{" +
                "id=" + id +
                '}';
    }
}


