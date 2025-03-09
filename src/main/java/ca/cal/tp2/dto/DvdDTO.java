package ca.cal.tp2.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data()
public class DvdDTO extends DocumentDTO {
    private String directeur;
    private int duree;
    private String note;

    public DvdDTO(String titre, int nombreExemplaires, String directeur,
               int duree, String note) {
        super(titre, nombreExemplaires);
        this.directeur = directeur;
        this.duree = duree;
        this.note = note;
    }

    public DvdDTO(Integer id, String titre, int nombreExemplaires,
               String directeur, int duree, String note) {
        super(id, titre, nombreExemplaires);
        this.directeur = directeur;
        this.duree = duree;
        this.note = note;
    }
}
