package ca.cal.tp2.dao;

import ca.cal.tp2.DBManager;
import ca.cal.tp2.modele.Document;
import ca.cal.tp2.modele.Emprunt;
import ca.cal.tp2.modele.EmpruntDocument;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmpruntDocumentDAOJPA implements EmpruntDocumentDAO{
    private LivreDAOJPA livreDAOJPA;
    private CdDAOJPA cdDAOJPA;
    private DvdDAOJPA dvdDAOJPA;

    @Override
    public EmpruntDocument retourner(Emprunt emprunt) {
        EntityManager entityManager = DBManager.getEntityManager();

        String sql = "SELECT empDoc FROM EmpruntDocument empDoc WHERE empDoc.emprunt = :emprunt";

        TypedQuery<EmpruntDocument> query = entityManager.createQuery(sql, EmpruntDocument.class);

        EmpruntDocument empruntDocument = query.getSingleResult();

        empruntDocument.setDocument(obtenirDocument(empruntDocument));

        entityManager.close();
        return empruntDocument;
    }

    private Document obtenirDocument(EmpruntDocument empruntDocument) {
        Document document = null;

        try {
            document = livreDAOJPA.rechercherPar(empruntDocument.getId());
        }
        catch (NoResultException ex) {
            try {
                document = dvdDAOJPA.rechercherPar(empruntDocument.getId());
            }
            catch (NoResultException ex2) {
                try {
                    document = cdDAOJPA.rechercherPar(empruntDocument.getId());
                }
                catch (NoResultException ex3) {
                    return document;
                }
            }
        }

        return document;
    }
}
