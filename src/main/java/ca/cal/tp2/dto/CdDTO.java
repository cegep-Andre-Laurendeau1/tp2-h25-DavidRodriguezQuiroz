package ca.cal.tp2.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data()
public class CdDTO extends DocumentDTO {
    private String artiste;
    private String genre;
    private int duree;

    public CdDTO(String titre, int nombreExemplaires,
              String artiste, String genre, int duree) {
        super(titre, nombreExemplaires);
        this.artiste = artiste;
        this.genre = genre;
        this.duree = duree;
    }

    public CdDTO(Long id, String titre, int nombreExemplaires,
              String artiste, String genre, int duree) {
        super(id, titre, nombreExemplaires);
        this.artiste = artiste;
        this.genre = genre;
        this.duree = duree;
    }
}