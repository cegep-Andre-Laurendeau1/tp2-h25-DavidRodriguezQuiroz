package ca.cal.tp2.dao;

import ca.cal.tp2.modele.Emprunteur;

public interface EmprunteurDAO {
    void enregistrer(Emprunteur emprunteur);

    Emprunteur rechercher(int id);
}
