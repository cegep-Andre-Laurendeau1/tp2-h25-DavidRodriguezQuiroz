package ca.cal.tp2.modele;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Emprunteur extends Utilisateur {
    @OneToMany(mappedBy = "emprunteur", cascade = CascadeType.ALL)
    private List<Emprunt> emprunts;

    @OneToMany(mappedBy = "emprunteur")
    private List<Amande> amandes;

    public Emprunteur(Integer id, String nom, String email, String numTel,
                      List<Emprunt> emprunts, List<Amande> amandes) {
        super(id, nom, email, numTel);
        this.emprunts = emprunts;
        this.amandes = amandes;
    }
}
