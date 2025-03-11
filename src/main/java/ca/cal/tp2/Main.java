package ca.cal.tp2;
import ca.cal.tp2.dao.*;
import ca.cal.tp2.dto.CdDTO;
import ca.cal.tp2.dto.EmprunteurDTO;
import ca.cal.tp2.dto.LivreDTO;
import ca.cal.tp2.modele.*;
import ca.cal.tp2.service.EmprunteurService;
import ca.cal.tp2.service.PreposeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.sql.SQLException;
import java.util.List;

public class Main {
    private static final EmprunteurService empService;
    private static final PreposeService preposeService;

    static {
        EmprunteurDAO emprunteurDAO = new EmprunteurDAOJPA();

        LivreDAOJPA livreDAOJPA = new LivreDAOJPA();
        CdDAOJPA cdDAOJPA = new CdDAOJPA();
        DvdDAOJPA dvdDAOJPA = new DvdDAOJPA();
        EmpruntDocumentDAO empruntDocumentDAO = new EmpruntDocumentDAOJPA(livreDAOJPA,
                cdDAOJPA,
                dvdDAOJPA);

        EmpruntDAO empruntDAO = new EmpruntDAOJPA(empruntDocumentDAO);
        empService = new EmprunteurService(emprunteurDAO, empruntDAO, livreDAOJPA, dvdDAOJPA, cdDAOJPA);
        preposeService = new PreposeService(livreDAOJPA, dvdDAOJPA, cdDAOJPA);
    }

    public static void main(String[] args) throws SQLException {
        TcpServer.demarrerTcpServer();
        DBManager.getEntityManager();

        EmprunteurDTO emprunteurDTO = new EmprunteurDTO(null, "Chris", "chris@gmail.com",
                "514-334-9456", null);

        emprunteurDTO = empService.enregistrer(emprunteurDTO);

        LivreDTO livreDTO = new LivreDTO("Le seigneur des anneaux", 1, "9341234123",
                "J.K. Rowling", "Laplouf Edition", 1989, 842);

        CdDTO cdDTO = new CdDTO("Beggars Banquet", 1, "The Rolling Stones", "Rock", 5);

        livreDTO = preposeService.enregistrerLivre(livreDTO);
        cdDTO = preposeService.enregistrerCD(cdDTO);

        empService.emprunterDocument(emprunteurDTO, livreDTO);
        empService.emprunterDocument(emprunteurDTO, cdDTO);

        emprunteurDTO = empService.rechercherEmprunteur(1);
        System.out.println(emprunteurDTO);
    }
}
