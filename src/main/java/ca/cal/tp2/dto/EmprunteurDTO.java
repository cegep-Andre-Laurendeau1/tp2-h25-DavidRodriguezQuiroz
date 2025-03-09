package ca.cal.tp2.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class EmprunteurDTO {
    private Integer id;
    private String nom;
    private String email;
    private String numTel;
    private List<EmpruntDTO> emprunts;

    public EmprunteurDTO(Integer id, String nom, String email, String numTel, List<EmpruntDTO> emprunts) {
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
}
