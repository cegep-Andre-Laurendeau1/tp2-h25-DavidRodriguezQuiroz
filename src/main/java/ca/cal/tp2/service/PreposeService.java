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

    public LivreDTO enregistrerLivre(LivreDTO livreDTO) {
        Livre livre = Livre.toModele(livreDTO);
        return Livre.toDTO(livreDAO.enregistrer(livre));
    }

    public CdDTO enregistrerCD(CdDTO cdDTO) {
        CD cd = CD.toModele(cdDTO);
        return CD.toDTO(cdDAO.enregistrer(cd));
    }

    public DvdDTO enregistrerDVD(DvdDTO dvdDTO) {
        DVD dvd = DVD.toModele(dvdDTO);
        return DVD.toDTO(dvdDAO.enregistrer(dvd));
    }
}
