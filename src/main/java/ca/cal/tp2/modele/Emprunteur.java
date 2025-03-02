package ca.cal.tp2.modele;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Emprunteur extends Utilisateur {
    @OneToMany(mappedBy = "emprunteur")
    private List<Emprunt> emprunts;

    @OneToMany(mappedBy = "emprunteur")
    private List<Amande> amandes;
}
