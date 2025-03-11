package ca.cal.tp2.dao;

import ca.cal.tp2.modele.Document;
import ca.cal.tp2.modele.Emprunt;
import ca.cal.tp2.modele.Emprunteur;

import java.util.List;

public interface EmpruntDAO {
    Emprunt enregistrer(Emprunt emprunt);

    Emprunt rechercher(long id);

    List<Emprunt> retournerEmprunts(Emprunteur emprunteur);
}
