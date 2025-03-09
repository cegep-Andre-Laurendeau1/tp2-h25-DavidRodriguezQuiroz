package ca.cal.tp2;

import ca.cal.tp2.modele.CD;
import ca.cal.tp2.modele.Document;
import ca.cal.tp2.modele.Livre;

public enum LimiteSemaineEmprunt {
    LIVRE(3),
    CD(2),
    DVD(1);

    private int nbSemaines;

    LimiteSemaineEmprunt(int nbSemaines) {
        this.nbSemaines = nbSemaines;
    }

    public static int of(Document document) {
        if (document instanceof Livre)
            return LIVRE.nbSemaines;
        else if (document instanceof ca.cal.tp2.modele.CD)
            return CD.nbSemaines;
        else
            return DVD.nbSemaines;
    }
}
