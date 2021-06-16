package it.uniroma3.siw.model.base;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Date;

@Data
@MappedSuperclass
@NoArgsConstructor
public abstract class Persona {
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    private String luogoDiNascita;
    private Date dataDiNascita;

    public Persona(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }
}
