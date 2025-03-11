package ca.cal.tp2.modele;
import ca.cal.tp2.dto.EmpruntDTO;
import ca.cal.tp2.dto.EmprunteurDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Emprunteur extends Utilisateur {
    @OneToMany(mappedBy = "emprunteur", cascade = CascadeType.ALL)
    private List<Emprunt> emprunts;

    @OneToMany(mappedBy = "emprunteur")
    private List<Amande> amandes;

    public Emprunteur(Long id, String nom, String email, String numTel,
                      List<Emprunt> emprunts, List<Amande> amandes) {
        super(id, nom, email, numTel);
        this.emprunts = emprunts;
        this.amandes = amandes;
    }

    public void ajouterEmprunt(Emprunt emprunt) {
        if (this.emprunts == null) {
            this.emprunts = new ArrayList<>();
        }
        this.emprunts.add(emprunt);
        emprunt.setEmprunteur(this);
    }

    public void enleverEmprunt() {

    }

    public static EmprunteurDTO toDTO(Emprunteur emprunteur) {
        if (emprunteur == null) {
            return null;
        }

        EmprunteurDTO emprunteurDTO = new EmprunteurDTO();
        emprunteurDTO.setId(emprunteur.getId());
        emprunteurDTO.setNom(emprunteur.getNom());
        emprunteurDTO.setEmail(emprunteur.getEmail());
        emprunteurDTO.setNumTel(emprunteur.getNumTel());

        List<EmpruntDTO> empruntDTOs = new ArrayList<>();
        List<Emprunt> emprunts = emprunteur.getEmprunts();

        if (emprunts != null) {
            empruntDTOs = emprunts.stream()
                    .map(emprunt -> Emprunt.toDTO(emprunt, emprunteurDTO))
                    .collect(Collectors.toList());
        }

        emprunteurDTO.setEmprunts(empruntDTOs);
        return emprunteurDTO;
    }

    public static Emprunteur toModele(EmprunteurDTO emprunteurDTO) {
        if (emprunteurDTO == null) {
            return null;
        }

        Emprunteur emprunteur = new Emprunteur();
        emprunteur.setId(emprunteurDTO.getId());
        emprunteur.setNom(emprunteurDTO.getNom());
        emprunteur.setEmail(emprunteurDTO.getEmail());
        emprunteur.setNumTel(emprunteurDTO.getNumTel());

        List<Emprunt> emprunts = new ArrayList<>();
        List<EmpruntDTO> empruntsDTOs = emprunteurDTO.getEmprunts();

        if (empruntsDTOs != null) {
            emprunts = empruntsDTOs.stream()
                    .map(empruntDTO -> Emprunt.toModele(empruntDTO, emprunteur))
                    .collect(Collectors.toList());
        }

        emprunteur.setEmprunts(emprunts);
        return emprunteur;
    }
}
