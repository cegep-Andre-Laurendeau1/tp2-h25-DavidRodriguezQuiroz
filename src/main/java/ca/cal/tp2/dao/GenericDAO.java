package ca.cal.tp2.dao;

import ca.cal.tp2.DBManager;
import jakarta.persistence.EntityManager;

public abstract class GenericDAO<T> {
    public void enregistrer(T t) {
        EntityManager entityManager = DBManager.commencerTransaction();

        entityManager.persist(t);

        DBManager.finirTransaction(entityManager);
    }
}
