package ca.cal.tp2.modele;

import ca.cal.tp2.dto.EmpruntDTO;
import ca.cal.tp2.dto.EmpruntDocumentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = {"document", "emprunt"})
)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpruntDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn
    private Document document;
    @OneToOne
    @JoinColumn
    private Emprunt emprunt;
    private LocalDate dateRetourActuelle;
    private LocalDate dateRetourPrevu;
    private String status;

    public static EmpruntDocumentDTO toDTO(EmpruntDocument empruntDocument, EmpruntDTO empruntDTO) {
        EmpruntDocumentDTO empruntDocumentDTO = new EmpruntDocumentDTO();
        empruntDocumentDTO.setId(empruntDocument.getId());
        empruntDocumentDTO.setDocumentDTO(Document.toDTO(empruntDocument.getDocument()));
        empruntDocumentDTO.setEmpruntDTO(empruntDTO);
        empruntDocumentDTO.setDateRetourActuelle(empruntDocument.getDateRetourActuelle());
        empruntDocumentDTO.setDateRetourPrevu(empruntDocument.getDateRetourPrevu());
        empruntDocumentDTO.setStatus(empruntDocument.getStatus());
        return empruntDocumentDTO;
    }

    public static EmpruntDocument toModele(EmpruntDocumentDTO empruntDocumentDTO, Emprunt emprunt) {
        EmpruntDocument empruntDocument = new EmpruntDocument();
        empruntDocument.setId(empruntDocumentDTO.getId());
        empruntDocument.setDocument(Document.toModele(empruntDocumentDTO.getDocumentDTO()));
        empruntDocument.setEmprunt(emprunt);
        empruntDocument.setDateRetourActuelle(empruntDocumentDTO.getDateRetourActuelle());
        empruntDocument.setDateRetourPrevu(empruntDocumentDTO.getDateRetourPrevu());
        empruntDocument.setStatus(empruntDocumentDTO.getStatus());
        return empruntDocument;
    }
}
