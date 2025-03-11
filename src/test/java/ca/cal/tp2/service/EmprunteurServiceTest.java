package ca.cal.tp2.service;

import ca.cal.tp2.DBManager;
import ca.cal.tp2.TcpServer;
import ca.cal.tp2.dao.*;
import ca.cal.tp2.dto.CdDTO;
import ca.cal.tp2.dto.DvdDTO;
import ca.cal.tp2.dto.EmprunteurDTO;
import ca.cal.tp2.dto.LivreDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmprunteurServiceTest {
    private static EmprunteurService emprunteurService;
    private static PreposeService preposeService;

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
    void rechercherLivres() {
        LivreDTO livreDTO = new LivreDTO("Le roi lion", 2, "9341234123",
                "J.K. Rowling", "Laplouf Edition", 1997, 300);

        LivreDTO livreDTO2 = new LivreDTO("Le roi lion 2", 2, "934122343",
                "J.K. Rowling", "Laplouf Edition", 2000, 450);

        preposeService.enregistrerLivre(livreDTO);
        preposeService.enregistrerLivre(livreDTO2);

        LivreDTO critereRecherche = new LivreDTO();
        critereRecherche.setAuteur("J.K. Rowling");
        critereRecherche.setTitre("roi");
        critereRecherche.setAnneePublication(-1);
        List<LivreDTO> livres = emprunteurService.rechercherLivres(critereRecherche);

        assertEquals(2, livres.size());
        assertEquals("Le roi lion 2", livres.get(1).getTitre());
        assertEquals("9341234123", livres.get(0).getISBN());

        critereRecherche = new LivreDTO();
        critereRecherche.setAuteur("J.K. Rowlin");
        critereRecherche.setTitre("lion");
        critereRecherche.setAnneePublication(-1);
        livres = emprunteurService.rechercherLivres(critereRecherche);

        assertEquals(0, livres.size());
    }

    @Test
    void rechercherCDs() {
        CdDTO cdDTO = new CdDTO("Love me", 2, "The Beatles", "Rock", 180);
        CdDTO cdDTO2 = new CdDTO("Love me again", 2, "The Beatles", "Rock", 180);

        preposeService.enregistrerCD(cdDTO);
        preposeService.enregistrerCD(cdDTO2);

        CdDTO critereRecherche = new CdDTO();
        critereRecherche.setTitre("again");
        critereRecherche.setArtiste("The Beatles");
        List<CdDTO> cds = emprunteurService.rechercherCDs(critereRecherche);

        assertEquals(1, cds.size());
        assertEquals("The Beatles", cds.get(0).getArtiste());
        assertEquals("Love me again", cds.get(0).getTitre());

        critereRecherche = new CdDTO();
        critereRecherche.setTitre("again");
        critereRecherche.setArtiste("The Beat");
        cds = emprunteurService.rechercherCDs(critereRecherche);

        assertEquals(0, cds.size());
    }

    @Test
    void rechercherDVDs() {
        DvdDTO dvdDTO = new DvdDTO("The Matrix", 2, "Wachowski", 180, "5");
        DvdDTO dvdDTO2 = new DvdDTO("The Matrix is back", 2, "Skichowwa", 180, "2");

        preposeService.enregistrerDVD(dvdDTO);
        preposeService.enregistrerDVD(dvdDTO2);

        DvdDTO critereRecherche = new DvdDTO();
        critereRecherche.setTitre("The Matrix");
        critereRecherche.setDirecteur("Wachowski");

        List<DvdDTO> dvds = emprunteurService.rechercherDVDs(critereRecherche);

        assertEquals(1, dvds.size());
        assertEquals("Wachowski", dvds.get(0).getDirecteur());
        assertEquals("5", dvds.get(0).getNote());

        critereRecherche.setDirecteur(null);

        dvds = emprunteurService.rechercherDVDs(critereRecherche);

        assertEquals(2, dvds.size());

        critereRecherche.setDirecteur("sdasdadad");

        dvds = emprunteurService.rechercherDVDs(critereRecherche);

        assertEquals(0, dvds.size());
    }

    @Test
    void emprunterDocument() {
        EmprunteurDTO emprunteurDTO = new EmprunteurDTO(null, "Robert", "robert@gmail.com",
                "438-213-5345", null);

        EmprunteurDTO emprunteurDTO2 = new EmprunteurDTO(null, "Maude", "maude@gmail.com",
                "514-242-2345", null);

        emprunteurDTO = emprunteurService.enregistrer(emprunteurDTO);
        emprunteurDTO2 = emprunteurService.enregistrer(emprunteurDTO2);

        LivreDTO livreDTO = new LivreDTO("The hobbit", 2, "9341234123",
                "J.K. Rowling", "Laplouf Edition", 1997, 300);

        CdDTO cdDTO = new CdDTO("The clock", 1, "The Beatles", "Rock", 180);

        livreDTO = preposeService.enregistrerLivre(livreDTO);
        cdDTO = preposeService.enregistrerCD(cdDTO);

        emprunteurDTO = emprunteurService.emprunterDocument(emprunteurDTO, livreDTO);
        emprunteurDTO = emprunteurService.emprunterDocument(emprunteurDTO, cdDTO);

        assertEquals(2, emprunteurDTO.getEmprunts().size());
        assertEquals("The clock", emprunteurDTO.getEmprunts().get(1).getEmpruntDocumentDTO().getDocumentDTO().getTitre());

        emprunteurDTO = emprunteurService.rechercherEmprunteur(emprunteurDTO.getId());

        assertEquals(2, emprunteurDTO.getEmprunts().size());
        assertEquals("The hobbit", emprunteurDTO.getEmprunts().get(0).getEmpruntDocumentDTO().getDocumentDTO().getTitre());
        assertEquals("The clock", emprunteurDTO.getEmprunts().get(1).getEmpruntDocumentDTO().getDocumentDTO().getTitre());

        emprunteurDTO2 = emprunteurService.emprunterDocument(emprunteurDTO2, livreDTO);
        emprunteurDTO2 = emprunteurService.emprunterDocument(emprunteurDTO2, cdDTO);

        assertEquals(1, emprunteurDTO2.getEmprunts().size());
        assertEquals("The hobbit", emprunteurDTO2.getEmprunts().get(0).getEmpruntDocumentDTO().getDocumentDTO().getTitre());

        emprunteurDTO2 = emprunteurService.rechercherEmprunteur(emprunteurDTO2.getId());

        assertEquals(1, emprunteurDTO2.getEmprunts().size());
        assertEquals("The hobbit", emprunteurDTO2.getEmprunts().get(0).getEmpruntDocumentDTO().getDocumentDTO().getTitre());
    }
}