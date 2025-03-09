package ca.cal.tp2.service;

import ca.cal.tp2.LimiteSemaineEmprunt;
import ca.cal.tp2.dao.DocumentDAO;
import ca.cal.tp2.dao.EmpruntDAO;
import ca.cal.tp2.dao.EmprunteurDAO;
import ca.cal.tp2.modele.*;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public class EmprunteurService {
    private EmprunteurDAO emprunteurDAO;
    private EmpruntDAO empruntDAO;
    private DocumentDAO<Livre> livreDAO;
    private DocumentDAO<DVD> dvdDAO;
    private DocumentDAO<CD> cdDAO;

    public void enregistrer(Emprunteur emprunteur) {
        emprunteurDAO.enregistrer(emprunteur);
    }

    public Emprunteur rechercher(int id) {
        Emprunteur emprunteur = emprunteurDAO.rechercher(id);

        if (emprunteur != null)
            emprunteur.setEmprunts(empruntDAO.retournerEmprunts(emprunteur));

        return emprunteur;
    }

    public List<Livre> rechercherLivres(Livre livre) {
        return livreDAO.rechercher(livre);
    }

    public List<CD> rechercherCDs(CD cd) {
        return cdDAO.rechercher(cd);
    }

    public List<DVD> rechercherDVDs(DVD dvd) {
        return dvdDAO.rechercher(dvd);
    }

    public void emprunterDocument(Emprunteur emprunteur, Document document) {
        Emprunt emprunt = new Emprunt();

        EmpruntDocument empruntDocument = new EmpruntDocument();
        empruntDocument.setDocument(document);
        int nbSemaines = LimiteSemaineEmprunt.of(document);
        empruntDocument.setDateRetourPrevu(LocalDate.now().plusWeeks(nbSemaines));

        emprunt.setEmpruntDocument(empruntDocument);
        emprunt.setDateEmprunt(LocalDate.now());

        emprunteur.ajouterEmprunt(emprunt);

        this.empruntDAO.enregistrer(emprunt);
    }

}
