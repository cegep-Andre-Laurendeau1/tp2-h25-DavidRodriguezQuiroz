package ca.cal.tp2.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data()
public class LivreDTO extends DocumentDTO {
    private String ISBN;
    private String auteur;
    private String editeur;
    private int anneePublication;
    private int nbPages;

    public LivreDTO(Integer id, String titre, int nombreExemplaires, String ISBN,
                 String auteur, String editeur, int anneePublication, int nbPages) {
        super(id, titre, nombreExemplaires);
        this.ISBN = ISBN;
        this.auteur = auteur;
        this.editeur = editeur;
        this.anneePublication = anneePublication;
        this.nbPages = nbPages;
    }

    public LivreDTO(String titre, int nombreExemplaires, String ISBN, String auteur,
                 String editeur, int anneePublication, int nbPages) {
        super(titre, nombreExemplaires);
        this.ISBN = ISBN;
        this.auteur = auteur;
        this.editeur = editeur;
        this.anneePublication = anneePublication;
        this.nbPages = nbPages;
    }
}
