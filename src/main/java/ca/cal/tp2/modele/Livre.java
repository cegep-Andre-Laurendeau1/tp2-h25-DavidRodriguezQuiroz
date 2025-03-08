package ca.cal.tp2.modele;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data()
public class Livre extends Document {
    private String ISBN;
    private String auteur;
    private String editeur;
    private int anneePublication;
    private int nbPages;

    public Livre(Integer id, String titre, int nombreExemplaires, String ISBN,
                 String auteur, String editeur, int anneePublication, int nbPages) {
        super(id, titre, nombreExemplaires);
        this.ISBN = ISBN;
        this.auteur = auteur;
        this.editeur = editeur;
        this.anneePublication = anneePublication;
        this.nbPages = nbPages;
    }

    public Livre(String titre, int nombreExemplaires, String ISBN, String auteur,
                 String editeur, int anneePublication, int nbPages) {
        super(titre, nombreExemplaires);
        this.ISBN = ISBN;
        this.auteur = auteur;
        this.editeur = editeur;
        this.anneePublication = anneePublication;
        this.nbPages = nbPages;
    }
}
