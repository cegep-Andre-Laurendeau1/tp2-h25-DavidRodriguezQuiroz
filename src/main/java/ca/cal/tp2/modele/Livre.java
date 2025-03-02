package ca.cal.tp2.modele;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data()
public class Livre extends Document {
    private String ISBN;
    private String auteur;
    private String editeur;
    private int nbPages;

    public Livre(String titre, int nombreExemplaires, String ISBN, String auteur,
                 String editeur, int nbPages) {
        super(titre, nombreExemplaires);
        this.ISBN = ISBN;
        this.auteur = auteur;
        this.editeur = editeur;
        this.nbPages = nbPages;
    }
}
