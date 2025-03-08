package ca.cal.tp2.dao;

import ca.cal.tp2.modele.Document;
import ca.cal.tp2.modele.Emprunt;
import ca.cal.tp2.modele.Emprunteur;

public interface EmpruntDAO {
    void enregistrer(Emprunt emprunt);
}
