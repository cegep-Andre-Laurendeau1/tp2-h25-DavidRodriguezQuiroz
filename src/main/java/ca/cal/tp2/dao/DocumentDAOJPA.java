package ca.cal.tp2.dao;
import ca.cal.tp2.DBManager;
import ca.cal.tp2.modele.EmpruntDocument;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DocumentDAOJPA<T> extends GenericDAO<T> implements DocumentDAO<T>{

    @Override
    public List<T> rechercher(T document) {
        EntityManager entityManager = DBManager.commencerTransaction();

        TypedQuery<T> query = entityManager.createQuery(getSql(document), getClassType());

        setParams(query, document);
        List<T> documents = query.getResultList();

        DBManager.finirTransaction(entityManager);
        return documents;
    }

    @Override
    protected abstract Class<T> getClassType();

    protected abstract String getSql(T document);

    protected abstract void setParams(TypedQuery<T> query, T document);

    @Override
    public T rechercherPar(int empDocId) {
        EntityManager entityManager = DBManager.getEntityManager();

        String sql = "SELECT d FROM " + getNomTable() + " d WHERE d.id = :empDocId";

        TypedQuery<T> query = entityManager.createQuery(sql, getClassType());

        T document = query.getSingleResult();

        entityManager.close();
        return document;
    }

    protected abstract String getNomTable();

    protected String formatterSQL(List<String> statements) {
        statements = statements.stream().filter((s) -> !s.isEmpty()).collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < statements.size(); i++) {
            if (i == 0) {
                stringBuilder.append(statements.get(i));
                continue;
            }
            stringBuilder.append(" AND").append(statements.get(i));
        }

        return stringBuilder.toString();
    }
}
