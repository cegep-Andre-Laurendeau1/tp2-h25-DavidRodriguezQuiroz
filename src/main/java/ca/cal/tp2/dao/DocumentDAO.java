package ca.cal.tp2.dao;

import ca.cal.tp2.modele.Document;
import ca.cal.tp2.modele.EmpruntDocument;

import java.util.List;

public interface DocumentDAO<T> {
    T enregistrer(T document);

    List<T> rechercher(T document);

    T rechercher(long id);

    T rechercherPar(long empDocId);
}
