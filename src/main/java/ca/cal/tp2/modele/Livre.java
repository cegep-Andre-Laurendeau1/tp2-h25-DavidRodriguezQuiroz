package ca.cal.tp2.modele;

import ca.cal.tp2.dto.LivreDTO;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public Livre(Long id, String titre, int nombreExemplaires, String ISBN,
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

    public static Livre toModele(LivreDTO livreDTO) {
        if (livreDTO == null) {
            return null;
        }
        return new Livre(livreDTO.getId(), livreDTO.getTitre(), livreDTO.getNombreExemplaires(),
                livreDTO.getISBN(), livreDTO.getAuteur(), livreDTO.getEditeur(),
                livreDTO.getAnneePublication(), livreDTO.getNbPages());
    }

    public static LivreDTO toDTO(Livre livre) {
        if (livre == null) {
            return null;
        }
        return new LivreDTO(livre.getId(), livre.getTitre(), livre.getNombreExemplaires(),
                livre.getISBN(), livre.getAuteur(), livre.getEditeur(),
                livre.getAnneePublication(), livre.getNbPages());
    }

    public static List<LivreDTO> toDTOs(List<Livre> livres) {
        return livres.stream().map(Livre::toDTO).collect(Collectors.toList());
    }
}
