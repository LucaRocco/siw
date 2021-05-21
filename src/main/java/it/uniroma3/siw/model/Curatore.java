package it.uniroma3.siw.model;

import it.uniroma3.siw.model.base.Persona;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Curatore extends Persona {

    @Id
    private String matricola;
    private String email;
    private String numeroDiTelefono;
    @OneToMany(mappedBy = "curatore")
    private List<Collezione> collezioniGestite;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    private String luogoDiNascita;
    private Date dataDiNascita;

    public Curatore(String nome, String cognome) {
        super(nome, cognome);
    }

}
