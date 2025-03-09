package ca.cal.tp2.dao;

import ca.cal.tp2.modele.Emprunt;
import ca.cal.tp2.modele.EmpruntDocument;

public interface EmpruntDocumentDAO {
    EmpruntDocument retourner(Emprunt emprunt);
}
