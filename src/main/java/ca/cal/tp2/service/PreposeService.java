package ca.cal.tp2.service;

import ca.cal.tp2.dao.DocumentDAO;
import ca.cal.tp2.modele.CD;
import ca.cal.tp2.modele.DVD;
import ca.cal.tp2.modele.Livre;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PreposeService {
    private DocumentDAO<Livre> livreDAO;
    private DocumentDAO<DVD> dvdDAO;
    private DocumentDAO<CD> cdDAO;

    public void enregistrerLivre(Livre livre) {
        livreDAO.enregistrer(livre);
    }

    public void enregistrerCD(CD cd) {
        cdDAO.enregistrer(cd);
    }

    public void enregistrerDVD(DVD dvd) {
        dvdDAO.enregistrer(dvd);
    }
}
