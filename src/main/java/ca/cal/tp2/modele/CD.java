package ca.cal.tp2.modele;

import ca.cal.tp2.dto.CdDTO;
import ca.cal.tp2.dto.LivreDTO;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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

    public static CD toModele(CdDTO cdDTO) {
        if (cdDTO == null) {
            return null;
        }
        return new CD(cdDTO.getId(), cdDTO.getTitre(), cdDTO.getNombreExemplaires(),
                cdDTO.getArtiste(), cdDTO.getGenre(), cdDTO.getDuree());
    }

    public static CdDTO toDTO(CD cd) {
        if (cd == null) {
            return null;
        }
        return new CdDTO(cd.getId(), cd.getTitre(), cd.getNombreExemplaires(),
                cd.getArtiste(), cd.getGenre(), cd.getDuree());
    }

    public static List<CdDTO> toDTOs(List<CD> cds) {
        return cds.stream().map(CD::toDTO).collect(Collectors.toList());
    }
}
