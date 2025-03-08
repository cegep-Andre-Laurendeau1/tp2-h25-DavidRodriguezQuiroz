package ca.cal.tp2.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = {"document", "emprunt"})
)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpruntDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn
    private Document document;
    @OneToOne
    @JoinColumn
    private Emprunt emprunt;
    private LocalDate dateRetourActuelle;
    private LocalDate dateRetourPrevu;
    private String status;
}
