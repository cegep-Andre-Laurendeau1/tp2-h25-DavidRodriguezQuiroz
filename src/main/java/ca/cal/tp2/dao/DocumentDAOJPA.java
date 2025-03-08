package ca.cal.tp2.dao;
import ca.cal.tp2.DBManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DocumentDAOJPA<T> extends GenericDAO<T> implements DocumentDAO<T>{

    @Override
    public List<T> rechercher(T document) {
        EntityManager entityManager = DBManager.getEntityManager();

        TypedQuery<T> query = entityManager.createQuery(getSql(document), getClassType());

        setParams(query, document);
        List<T> documents = query.getResultList();

        entityManager.close();
        return documents;
    }

    protected abstract Class<T> getClassType();

    protected abstract String getSql(T document);

    protected abstract void setParams(TypedQuery<T> query, T document);

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
