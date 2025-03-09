package ca.cal.tp2.service;

import ca.cal.tp2.dao.DocumentDAO;
import ca.cal.tp2.dto.CdDTO;
import ca.cal.tp2.dto.DvdDTO;
import ca.cal.tp2.dto.LivreDTO;
import ca.cal.tp2.modele.CD;
import ca.cal.tp2.modele.DVD;
import ca.cal.tp2.modele.Livre;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PreposeService {
    private DocumentDAO<Livre> livreDAO;
    private DocumentDAO<DVD> dvdDAO;
    private DocumentDAO<CD> cdDAO;

    public void enregistrerLivre(LivreDTO livreDTO) {
        livreDAO.enregistrer(Livre.toModele(livreDTO));
    }

    public void enregistrerCD(CdDTO cdDTO) {
        cdDAO.enregistrer(CD.toModele(cdDTO));
    }

    public void enregistrerDVD(DvdDTO dvdDTO) {
        dvdDAO.enregistrer(DVD.toModele(dvdDTO));
    }
}
