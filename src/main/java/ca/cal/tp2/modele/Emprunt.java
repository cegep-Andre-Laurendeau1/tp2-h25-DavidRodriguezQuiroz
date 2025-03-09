package ca.cal.tp2.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne(mappedBy = "emprunt", cascade = CascadeType.ALL)
    private EmpruntDocument empruntDocument;
    private LocalDate dateEmprunt;
    private String status;
    @ManyToOne
    @JoinColumn
    private Emprunteur emprunteur;

    public void setEmpruntDocument(EmpruntDocument empruntDocument) {
        this.empruntDocument = empruntDocument;
        this.empruntDocument.setEmprunt(this);
    }
}
