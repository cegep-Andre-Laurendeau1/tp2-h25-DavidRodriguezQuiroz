package ca.cal.tp2.modele;

import ca.cal.tp2.dto.EmpruntDTO;
import ca.cal.tp2.dto.EmprunteurDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "emprunt", cascade = CascadeType.ALL)
    private EmpruntDocument empruntDocument;
    private LocalDate dateEmprunt;
    private String status;
    @ManyToOne
    @JoinColumn
    private Emprunteur emprunteur;

    public void setEmpruntDocument(EmpruntDocument empruntDocument) {
        this.empruntDocument = empruntDocument;
        this.empruntDocument.setEmprunt(this);
    }

    public static EmpruntDTO toDTO(Emprunt emprunt, EmprunteurDTO emprunteurDTO) {
        EmpruntDTO empruntDTO = new EmpruntDTO();
        empruntDTO.setId(emprunt.getId());
        empruntDTO.setEmpruntDocumentDTO(EmpruntDocument.toDTO(emprunt.getEmpruntDocument(), empruntDTO));
        empruntDTO.setDateEmprunt(emprunt.getDateEmprunt());
        empruntDTO.setStatus(emprunt.getStatus());
        empruntDTO.setEmprunteurDTO(emprunteurDTO);
        return empruntDTO;
    }

    public static Emprunt toModele(EmpruntDTO empruntDTO, Emprunteur emprunteur) {
        Emprunt emprunt = new Emprunt();
        emprunt.setId(empruntDTO.getId());
        emprunt.setEmpruntDocument(EmpruntDocument.toModele(empruntDTO.getEmpruntDocumentDTO(), emprunt));
        emprunt.setDateEmprunt(empruntDTO.getDateEmprunt());
        emprunt.setStatus(empruntDTO.getStatus());
        emprunt.setEmprunteur(emprunteur);
        return emprunt;
    }
}
