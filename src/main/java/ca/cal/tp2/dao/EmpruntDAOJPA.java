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

    private EmpruntDocumentDAO empruntDocumentDAO;

    public EmpruntDAOJPA(EmpruntDocumentDAO empruntDocumentDAO) {
        this.empruntDocumentDAO = empruntDocumentDAO;
    }

    @Override
    public void enregistrer(Emprunt emprunt) {
        Document document = emprunt.getEmpruntDocument().getDocument();

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

    @Override
    public List<Emprunt> retournerEmprunts(Emprunteur emprunteur) {
        EntityManager entityManager = DBManager.getEntityManager();

        String sql = "SELECT e FROM Emprunt e WHERE e.emprunteur = :emprunteur";

        TypedQuery<Emprunt> query = entityManager.createQuery(sql, Emprunt.class);
        query.setParameter("emprunteur", emprunteur);
        List<Emprunt> emprunts = query.getResultList();

        if (!emprunts.isEmpty())
         emprunts.forEach((emprunt -> emprunt.setEmpruntDocument(empruntDocumentDAO.retourner(emprunt))));

        entityManager.close();
        return emprunts;
    }

    @Override
    protected Class<Emprunt> getClassType() {
        return Emprunt.class;
    }
}
