package it.unirom3.siw.model;

import it.unirom3.siw.model.base.Persona;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Curatore extends Persona {

    @Id
    private String matricola;
    private String email;
    private String numeroDiTelefono;
    @OneToMany(mappedBy = "curatore")
    private List<Collezione> collezioniGestite;

    public Curatore() {
    }

    public Curatore(String nome, String cognome) {
        super(nome, cognome);
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroDiTelefono() {
        return numeroDiTelefono;
    }

    public void setNumeroDiTelefono(String numeroDiTelefono) {
        this.numeroDiTelefono = numeroDiTelefono;
    }

    public List<Collezione> getCollezioniGestite() {
        return collezioniGestite;
    }

    public void setCollezioniGestite(List<Collezione> collezioniGestite) {
        this.collezioniGestite = collezioniGestite;
    }
}
