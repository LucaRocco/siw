package it.uniroma3.siw.model;

import javax.persistence.*;
import java.util.List;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Curatore getCuratore() {
        return curatore;
    }

    public void setCuratore(Curatore curatore) {
        this.curatore = curatore;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Opera> getOpere() {
        return opere;
    }

    public void setOpere(List<Opera> opere) {
        this.opere = opere;
    }
}
