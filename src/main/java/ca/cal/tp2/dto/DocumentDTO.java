package ca.cal.tp2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class DocumentDTO {
    private Long id;
    private String titre;
    private int nombreExemplaires;

    public DocumentDTO(String titre, int nombreExemplaires) {
        this.titre = titre;
        this.nombreExemplaires = nombreExemplaires;
    }
}
