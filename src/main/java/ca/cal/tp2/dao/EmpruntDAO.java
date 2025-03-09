package ca.cal.tp2.dao;

import ca.cal.tp2.modele.Emprunt;
import ca.cal.tp2.modele.Emprunteur;

import java.util.List;

public interface EmpruntDAO {
    void enregistrer(Emprunt emprunt);

    List<Emprunt> retournerEmprunts(Emprunteur emprunteur);
}
