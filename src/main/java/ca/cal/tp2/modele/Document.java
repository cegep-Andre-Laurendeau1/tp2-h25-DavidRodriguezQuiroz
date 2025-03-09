package ca.cal.tp2.modele;
import ca.cal.tp2.dto.CdDTO;
import ca.cal.tp2.dto.DocumentDTO;
import ca.cal.tp2.dto.DvdDTO;
import ca.cal.tp2.dto.LivreDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String titre;
    private int nombreExemplaires;

    public Document(String titre, int nombreExemplaires) {
        this.titre = titre;
        this.nombreExemplaires = nombreExemplaires;
    }

    public static Document toModele(DocumentDTO documentDTO) {
        if (documentDTO == null) {
            return null;
        }

        if (documentDTO instanceof CdDTO) {
            return CD.toModele((CdDTO) documentDTO);
        } else if (documentDTO instanceof DvdDTO) {
            return DVD.toModele((DvdDTO) documentDTO);
        } else if (documentDTO instanceof LivreDTO) {
            return Livre.toModele((LivreDTO) documentDTO);
        }
        return null;
    }

    public static DocumentDTO toDTO(Document document) {
        if (document == null) {
            return null;
        }

        if (document instanceof CD) {
            return CD.toDTO((CD) document);
        } else if (document instanceof DVD) {
            return DVD.toDTO((DVD) document);
        } else if (document instanceof Livre) {
            return Livre.toDTO((Livre) document);
        }
        return null;
    }
}
