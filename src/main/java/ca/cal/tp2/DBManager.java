package ca.cal.tp2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBManager {
    private static final String PERSISTENCE_UNIT_NAME = "tp2";
    private static final EntityManagerFactory EMF =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    public static EntityManager getEntityManager() {
        return EMF.createEntityManager();
    }

    public static EntityManager commencerTransaction() {
        EntityManager entityManager = EMF.createEntityManager();
        entityManager.getTransaction().begin();
        return entityManager;
    }

    public static void finirTransaction(EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}


