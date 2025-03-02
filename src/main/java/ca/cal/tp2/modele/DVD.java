package ca.cal.tp2.modele;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data()
public class DVD extends Document {
    private String directeur;
    private int duree;
    private String note;

    public DVD(String titre, int nombreExemplaires, String directeur,
               int duree, String note) {
        super(titre, nombreExemplaires);
        this.directeur = directeur;
        this.duree = duree;
        this.note = note;
    }

    public DVD(Integer id, String titre, int nombreExemplaires,
               String directeur, int duree, String note) {
        super(id, titre, nombreExemplaires);
        this.directeur = directeur;
        this.duree = duree;
        this.note = note;
    }
}
