package ca.cal.tp2.dto;

import ca.cal.tp2.modele.EmpruntDocument;
import ca.cal.tp2.modele.Emprunteur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpruntDTO {
    private Long id;
    private EmpruntDocumentDTO empruntDocumentDTO;
    private LocalDate dateEmprunt;
    private String status;
    private EmprunteurDTO emprunteurDTO;

    public void setEmpruntDocument(EmpruntDocumentDTO empruntDocumentDTO) {
        this.empruntDocumentDTO = empruntDocumentDTO;
        this.empruntDocumentDTO.setEmpruntDTO(this);
    }
}
