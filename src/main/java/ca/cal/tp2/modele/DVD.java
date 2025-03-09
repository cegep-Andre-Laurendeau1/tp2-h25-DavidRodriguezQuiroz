package ca.cal.tp2.modele;

import ca.cal.tp2.dto.DvdDTO;
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

    public static DVD toModele(DvdDTO dvdDTO) {
        if (dvdDTO == null) {
            return null;
        }
        return new DVD(dvdDTO.getId(), dvdDTO.getTitre(), dvdDTO.getNombreExemplaires(),
                dvdDTO.getDirecteur(), dvdDTO.getDuree(), dvdDTO.getNote());
    }

    public static DvdDTO toDTO(DVD dvd) {
        if (dvd == null) {
            return null;
        }
        return new DvdDTO(dvd.getId(), dvd.getTitre(), dvd.getNombreExemplaires(),
                dvd.getDirecteur(), dvd.getDuree(), dvd.getNote());
    }

    public static List<DvdDTO> toDTOs(List<DVD> dvds) {
        return dvds.stream().map(DVD::toDTO).collect(Collectors.toList());
    }
}
