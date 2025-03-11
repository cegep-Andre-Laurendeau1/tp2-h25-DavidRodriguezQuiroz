package ca.cal.tp2.dao;

import ca.cal.tp2.modele.Emprunteur;

public interface EmprunteurDAO {
    Emprunteur enregistrer(Emprunteur emprunteur);

    Emprunteur rechercher(long id);
}
