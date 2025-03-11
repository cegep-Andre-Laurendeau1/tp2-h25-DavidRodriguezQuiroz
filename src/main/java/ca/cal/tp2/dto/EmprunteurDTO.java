package ca.cal.tp2.dto;

import ca.cal.tp2.modele.Emprunt;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class EmprunteurDTO {
    private Long id;
    private String nom;
    private String email;
    private String numTel;
    private List<EmpruntDTO> emprunts;

    public EmprunteurDTO(Long id, String nom, String email, String numTel, List<EmpruntDTO> emprunts) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.numTel = numTel;
        this.emprunts = emprunts;
    }

    public void ajouterEmprunt(EmpruntDTO empruntDTO) {
        this.emprunts.add(empruntDTO);
        empruntDTO.setEmprunteurDTO(this);
    }

    @Override
    public String toString() {
        String description = String.format("Emprunteur: %s, Email: %s, Numéro de téléphone: %s\n",
                getNom(), getEmail(), getNumTel());

        if (emprunts != null && !emprunts.isEmpty()) {
            description += "Emprunts:\n";
            for (EmpruntDTO emprunt : emprunts) {
                description += emprunt.toString();
            }
        }

        return description;
    }
}
