package ca.cal.tp2.dao;

import ca.cal.tp2.modele.DVD;
import ca.cal.tp2.modele.Livre;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class DvdDAOJPA extends DocumentDAOJPA<DVD>{

    @Override
    public List<DVD> rechercher(DVD dvd) {
        return super.rechercher(dvd);
    }

    @Override
    protected Class<DVD> getClassType() {
        return DVD.class;
    }

    @Override
    protected String getSql(DVD dvd) {
        List<String> statements = new ArrayList<>();
        statements.add((dvd.getTitre() == null ? "" : " d.titre LIKE :titre"));
        statements.add((dvd.getDirecteur() == null ? "" : " d.directeur = :directeur"));

        String sql = "SELECT d FROM DVD d WHERE" + formatterSQL(statements);
        return sql;
    }

    @Override
    protected void setParams(TypedQuery<DVD> query, DVD dvd) {
        query.setParameter("titre", "%" + dvd.getTitre() + "%");
        query.setParameter("directeur", dvd.getDirecteur());
    }

    @Override
    protected String getNomTable() {
        return "DVD";
    }
}
