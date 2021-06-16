package it.uniroma3.siw.model;

import it.uniroma3.siw.model.base.Persona;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Curatore extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String matricola;
    private String email;
    private String numeroDiTelefono;
    @OneToMany(mappedBy = "curatore")
    private List<Collezione> collezioniGestite;

    public Curatore(String nome, String cognome) {
        super(nome, cognome);
    }

    @Override
    public String toString() {
        return "Curatore{" +
                "matricola='" + matricola + '\'' +
                '}';
    }
}
