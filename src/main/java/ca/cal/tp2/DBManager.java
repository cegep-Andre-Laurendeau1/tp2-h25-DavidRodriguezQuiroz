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
}


