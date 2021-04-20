package it.uniroma3.siw.model;

import it.uniroma3.siw.model.base.Persona;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.List;

@Entity
public class Artista extends Persona {

    @Id
    private Long id;

    private String luogoDiMorte;

    private Date dataDiMorte;

    private String nazionalita;

    @OneToMany(mappedBy = "autore")
    private List<Opera> opere;

    public Artista() {

    }

    public Artista(String nome, String cognome) {
        super(nome, cognome);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLuogoDiMorte() {
        return luogoDiMorte;
    }

    public void setLuogoDiMorte(String luogoDiMorte) {
        this.luogoDiMorte = luogoDiMorte;
    }

    public Date getDataDiMorte() {
        return dataDiMorte;
    }

    public void setDataDiMorte(Date dataDiMorte) {
        this.dataDiMorte = dataDiMorte;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }

    public List<Opera> getOpere() {
        return opere;
    }

    public void setOpere(List<Opera> opere) {
        this.opere = opere;
    }
}
