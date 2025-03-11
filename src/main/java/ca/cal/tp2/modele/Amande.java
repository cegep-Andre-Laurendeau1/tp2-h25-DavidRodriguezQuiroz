package ca.cal.tp2.modele;

import ca.cal.tp2.DBManager;
import ca.cal.tp2.TcpServer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Amande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double montant;
    private boolean status;
    private LocalDate dateCreation;
    @ManyToOne
    @JoinColumn
    private Emprunteur emprunteur;
}
