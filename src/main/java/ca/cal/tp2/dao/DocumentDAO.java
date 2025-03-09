package ca.cal.tp2.dao;

import ca.cal.tp2.modele.Document;
import ca.cal.tp2.modele.EmpruntDocument;

import java.util.List;

public interface DocumentDAO<T> {
    void enregistrer(T document);

    List<T> rechercher(T document);

    T rechercherPar(int empDocId);
}
