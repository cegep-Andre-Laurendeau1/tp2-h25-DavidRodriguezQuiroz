package ca.cal.tp2.dao;

import ca.cal.tp2.DBManager;
import ca.cal.tp2.modele.Document;
import ca.cal.tp2.modele.Emprunt;
import ca.cal.tp2.modele.EmpruntDocument;
import ca.cal.tp2.modele.Emprunteur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EmpruntDAOJPA extends GenericDAO<Emprunt> implements EmpruntDAO{

    @Override
    public void enregistrer(Emprunt emprunt) {
        Document document = emprunt.getEmpruntDocument().getDocument();
        System.out.println("sdasdad");
        if (exemplaireDisponible(document))
            super.enregistrer(emprunt);
    }

    private boolean exemplaireDisponible(Document document) {
        String sql = "SELECT empDoc FROM EmpruntDocument empDoc WHERE empDoc.document = :documentId";

        EntityManager entityManager = DBManager.getEntityManager();

        TypedQuery<EmpruntDocument> query = entityManager.createQuery(sql, EmpruntDocument.class);
        query.setParameter("documentId", document);

        List<EmpruntDocument> empruntDocuments = query.getResultList();
        entityManager.close();

        if (empruntDocuments.isEmpty())
            return true;

        Document premierDocumentRetourner = empruntDocuments.get(0).getDocument();
        return premierDocumentRetourner.getNombreExemplaires() > empruntDocuments.size();
    }
}
