package ca.cal.tp2.dao;

import ca.cal.tp2.DBManager;
import jakarta.persistence.EntityManager;

public abstract class GenericDAO<T> {
    public T enregistrer(T t) {
        EntityManager entityManager = DBManager.commencerTransaction();

        entityManager.persist(t);

        DBManager.finirTransaction(entityManager);

        return t;
    }

    public T rechercher(long id) {
        EntityManager entityManager = DBManager.getEntityManager();

        T entite = entityManager.find(getClassType(), id);

        entityManager.close();

        return entite;
    }

    protected abstract Class<T> getClassType();
}
