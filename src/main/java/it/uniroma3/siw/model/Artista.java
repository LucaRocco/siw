package it.uniroma3.siw.model;

import it.uniroma3.siw.model.base.Persona;
import lombok.*;

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

    private String luogoDiMorte;

    private Date dataDiMorte;

    private String nazionalita;

    @OneToMany(mappedBy = "autore")
    private List<Opera> opere;

    public Artista(String nome, String cognome) {
        super(nome, cognome);
    }

}


