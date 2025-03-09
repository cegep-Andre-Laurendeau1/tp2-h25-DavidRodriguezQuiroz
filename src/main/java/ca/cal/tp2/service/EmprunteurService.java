package ca.cal.tp2.service;

import ca.cal.tp2.LimiteSemaineEmprunt;
import ca.cal.tp2.dao.DocumentDAO;
import ca.cal.tp2.dao.EmpruntDAO;
import ca.cal.tp2.dao.EmprunteurDAO;
import ca.cal.tp2.dto.*;
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

    public EmprunteurDTO rechercher(int id) {
        Emprunteur emprunteur = emprunteurDAO.rechercher(id);

        if (emprunteur != null)
            emprunteur.setEmprunts(empruntDAO.retournerEmprunts(emprunteur));

        return Emprunteur.toDTO(emprunteur);
    }

    public List<LivreDTO> rechercherLivres(LivreDTO livreDTO) {
        List<Livre> livres = livreDAO.rechercher(Livre.toModele(livreDTO));
        return Livre.toDTOs(livres);
    }

    public List<CdDTO> rechercherCDs(CdDTO cd) {
        List<CD> cds = cdDAO.rechercher(CD.toModele(cd));
        return CD.toDTOs(cds);
    }

    public List<DvdDTO> rechercherDVDs(DvdDTO dvd) {
        List<DVD> dvds = dvdDAO.rechercher(DVD.toModele(dvd));
        return DVD.toDTOs(dvds);
    }

    public void emprunterDocument(EmprunteurDTO emprunteurDTO, DocumentDTO documentDTO) {
        Emprunteur emprunteur = Emprunteur.toModele(emprunteurDTO);

        Document document = Document.toModele(documentDTO);

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
