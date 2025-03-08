package ca.cal.tp2.dao;

import ca.cal.tp2.modele.Document;

import java.util.List;

public interface DocumentDAO<T> {
    void enregistrer(T document);

    List<T> rechercher(T document);
}
