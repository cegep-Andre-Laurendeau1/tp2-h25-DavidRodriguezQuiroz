package ca.cal.tp2.dao;

import ca.cal.tp2.DBManager;
import jakarta.persistence.EntityManager;

public abstract class GenericDAO<T> {
    public void enregistrer(T t) {
        EntityManager entityManager = DBManager.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(t);

        entityManager.getTransaction().commit();
    }
}
