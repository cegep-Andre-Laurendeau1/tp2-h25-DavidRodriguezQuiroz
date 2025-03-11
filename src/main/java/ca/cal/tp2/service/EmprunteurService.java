package ca.cal.tp2.service;

import ca.cal.tp2.LimiteSemaineEmprunt;
import ca.cal.tp2.dao.DocumentDAO;
import ca.cal.tp2.dao.EmpruntDAO;
import ca.cal.tp2.dao.EmprunteurDAO;
import ca.cal.tp2.dao.LivreDAOJPA;
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

    public EmprunteurDTO enregistrer(EmprunteurDTO emprunteurDTO) {
        Emprunteur emprunteur = Emprunteur.toModele(emprunteurDTO);
        return Emprunteur.toDTO(emprunteurDAO.enregistrer(emprunteur));
    }

    public EmprunteurDTO rechercherEmprunteur(long id) {
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

    public EmprunteurDTO emprunterDocument(EmprunteurDTO emprunteurDTO, DocumentDTO documentDTO) {
        Document document = Document.toModele(documentDTO);

        Emprunteur emprunteur = Emprunteur.toModele(emprunteurDTO);

        Emprunt emprunt = new Emprunt();

        EmpruntDocument empruntDocument = new EmpruntDocument();
        empruntDocument.setDocument(document);
        int nbSemaines = LimiteSemaineEmprunt.of(document);
        empruntDocument.setDateRetourPrevu(LocalDate.now().plusWeeks(nbSemaines));

        emprunt.setEmpruntDocument(empruntDocument);
        emprunt.setDateEmprunt(LocalDate.now());
        emprunt.setEmprunteur(emprunteur);

        if (this.empruntDAO.enregistrer(emprunt) != null)
            emprunteur.ajouterEmprunt(emprunt);

        return Emprunteur.toDTO(emprunteur);
    }

}
