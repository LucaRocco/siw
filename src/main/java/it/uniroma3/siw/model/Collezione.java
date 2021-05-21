package it.uniroma3.siw.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany(mappedBy = "collezioni")
    private List<Opera> opere;
}
