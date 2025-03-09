package ca.cal.tp2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpruntDocumentDTO  {
    private Integer id;
    private DocumentDTO documentDTO;
    private EmpruntDTO empruntDTO;
    private LocalDate dateRetourActuelle;
    private LocalDate dateRetourPrevu;
    private String status;
}
