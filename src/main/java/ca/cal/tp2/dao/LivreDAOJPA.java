package ca.cal.tp2.dao;

import ca.cal.tp2.modele.Document;
import ca.cal.tp2.modele.Livre;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class LivreDAOJPA extends DocumentDAOJPA<Livre> {

    @Override
    public List<Livre> rechercher(Livre livre) {
        return super.rechercher(livre);
    }

    @Override
    protected Class<Livre> getClassType() {
        return Livre.class;
    }

    @Override
    protected String getSql(Livre livre) {
        List<String> statements = new ArrayList<>();
        statements.add((livre.getTitre() == null ? "" : " d.titre LIKE :titre"));
        statements.add((livre.getAuteur() == null ? "" : " d.auteur = :auteur"));
        statements.add((livre.getAnneePublication() == -1 ? "" : " d.anneePublication = :annee"));

        String sql = "SELECT d FROM Livre d WHERE" + formatterSQL(statements);
        return sql;
    }

    @Override
    protected void setParams(TypedQuery<Livre> query, Livre livre) {
        query.setParameter("titre", "%" + livre.getTitre() + "%");
        query.setParameter("auteur", livre.getAuteur());
        query.setParameter("annee", livre.getAnneePublication());
    }
}
