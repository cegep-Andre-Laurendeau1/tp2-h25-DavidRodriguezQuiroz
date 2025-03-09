package ca.cal.tp2.dao;

import ca.cal.tp2.modele.CD;
import ca.cal.tp2.modele.Livre;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class CdDAOJPA extends DocumentDAOJPA<CD>{
    @Override
    public List<CD> rechercher(CD cd) {
        return super.rechercher(cd);
    }

    @Override
    protected Class<CD> getClassType() {
        return CD.class;
    }

    @Override
    protected String getSql(CD cd) {
        List<String> statements = new ArrayList<>();
        statements.add((cd.getTitre() == null ? "" : " c.titre LIKE :titre"));
        statements.add((cd.getArtiste() == null ? "" : " c.artiste = :artiste"));

        String sql = "SELECT c FROM CD c WHERE" + formatterSQL(statements);
        return sql;
    }

    @Override
    protected void setParams(TypedQuery<CD> query, CD cd) {
        query.setParameter("titre", "%" + cd.getTitre() + "%");
        query.setParameter("artiste", cd.getArtiste());
    }

    @Override
    protected String getNomTable() {
        return "CD";
    }
}
