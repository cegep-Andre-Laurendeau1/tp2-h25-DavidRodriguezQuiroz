package ca.cal.tp2.service;

import ca.cal.tp2.DBManager;
import ca.cal.tp2.TcpServer;
import ca.cal.tp2.dao.*;
import ca.cal.tp2.dto.CdDTO;
import ca.cal.tp2.dto.DvdDTO;
import ca.cal.tp2.dto.LivreDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PreposeServiceTest {
    private static PreposeService preposeService;
    private static EmprunteurService emprunteurService;

    @BeforeAll
    static void setUp() throws SQLException {
        TcpServer.demarrerTcpServer();
        DBManager.getEntityManager();

        EmprunteurDAO emprunteurDAO = new EmprunteurDAOJPA();

        LivreDAOJPA livreDAOJPA = new LivreDAOJPA();
        CdDAOJPA cdDAOJPA = new CdDAOJPA();
        DvdDAOJPA dvdDAOJPA = new DvdDAOJPA();
        EmpruntDocumentDAO empruntDocumentDAO = new EmpruntDocumentDAOJPA(livreDAOJPA,
                cdDAOJPA,
                dvdDAOJPA);

        EmpruntDAO empruntDAO = new EmpruntDAOJPA(empruntDocumentDAO);

        emprunteurService = new EmprunteurService(emprunteurDAO, empruntDAO, livreDAOJPA, dvdDAOJPA, cdDAOJPA);
        preposeService = new PreposeService(livreDAOJPA, dvdDAOJPA, cdDAOJPA);
    }

    @Test
    void enregistrerLivre() {
        LivreDTO livreDTO = new LivreDTO("Le roi lion", 2, "9341234123",
                "The Doctor", "Laplouf Edition", 1997, 300);

        livreDTO = preposeService.enregistrerLivre(livreDTO);

        LivreDTO critereRecherche = new LivreDTO();
        critereRecherche.setAnneePublication(-1);
        critereRecherche.setAuteur("The Doctor");
        List<LivreDTO> livres = emprunteurService.rechercherLivres(critereRecherche);

        assertEquals(livreDTO.getId(), livres.get(0).getId());
        assertEquals(1, livres.size());
        assertEquals("Le roi lion", livres.get(0).getTitre());
        assertEquals("9341234123", livres.get(0).getISBN());
    }

    @Test
    void enregistrerCD() {
        CdDTO cdDTO = new CdDTO("Sunshine", 2, "The Beatles", "Rock", 180);

        cdDTO = preposeService.enregistrerCD(cdDTO);

        CdDTO critereRecherche = new CdDTO();
        critereRecherche.setTitre("Sunshine");
        List<CdDTO> cds = emprunteurService.rechercherCDs(critereRecherche);

        assertEquals(cdDTO.getId(), cds.get(0).getId());
        assertEquals(1, cds.size());
        assertEquals("The Beatles", cds.get(0).getArtiste());
        assertEquals("Rock", cds.get(0).getGenre());
    }

    @Test
    void enregistrerDVD() {
        DvdDTO dvdDTO = new DvdDTO("Christmas", 2, "Wachowski", 180, "5");

        preposeService.enregistrerDVD(dvdDTO);

        DvdDTO critereRecherche = new DvdDTO();
        critereRecherche.setTitre("Christmas");

        List<DvdDTO> dvds = emprunteurService.rechercherDVDs(critereRecherche);

        assertEquals(1, dvds.size());
        assertEquals("Wachowski", dvds.get(0).getDirecteur());
        assertEquals("5", dvds.get(0).getNote());
    }
}