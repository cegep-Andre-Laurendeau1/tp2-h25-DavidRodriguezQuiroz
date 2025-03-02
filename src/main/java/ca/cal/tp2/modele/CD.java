package ca.cal.tp2.modele;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data()
public class CD extends Document {
    private String artiste;
    private String genre;
    private int duree;

    public CD(String titre, int nombreExemplaires,
              String artiste, String genre, int duree) {
        super(titre, nombreExemplaires);
        this.artiste = artiste;
        this.genre = genre;
        this.duree = duree;
    }

    public CD(Integer id, String titre, int nombreExemplaires,
              String artiste, String genre, int duree) {
        super(id, titre, nombreExemplaires);
        this.artiste = artiste;
        this.genre = genre;
        this.duree = duree;
    }
}
