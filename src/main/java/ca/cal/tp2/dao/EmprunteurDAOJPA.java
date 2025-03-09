package ca.cal.tp2.dao;
import ca.cal.tp2.modele.Emprunteur;


public class EmprunteurDAOJPA extends GenericDAO<Emprunteur> implements EmprunteurDAO{
    @Override
    protected Class<Emprunteur> getClassType() {
        return Emprunteur.class;
    }
}
