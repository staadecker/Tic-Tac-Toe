package tictactoe;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

import java.util.ArrayList;
import java.util.List;

public class Jeu {
    private final List<List<ReadOnlyObjectWrapper<CaseStatus>>> statusCase;

    private final ReadOnlyObjectWrapper<JeuStatus> statusJeu;

    public Jeu() {
        statusCase = creePlateauVide();
        statusJeu = new ReadOnlyObjectWrapper<>(JeuStatus.TOUR_CROIX);
    }

    void jouer(Position position) {
        ReadOnlyObjectWrapper<CaseStatus> caseAJouer = statusCase.get(position.rangee).get(position.colonne);

        if (statusJeu.get() == JeuStatus.TOUR_CROIX) {
            caseAJouer.set(CaseStatus.CROIX);
            statusJeu.set(JeuStatus.TOUR_CERCLE);
        } else if (statusJeu.get() == JeuStatus.TOUR_CERCLE) {
            caseAJouer.set(CaseStatus.CERCLE);
            statusJeu.set(JeuStatus.TOUR_CROIX);
        }
    }

    public ReadOnlyObjectProperty<JeuStatus> jeuProperty(){
        return statusJeu.getReadOnlyProperty();
    }

    public ReadOnlyObjectProperty<CaseStatus> caseProperty(Position position) {
        return statusCase.get(position.rangee).get(position.colonne).getReadOnlyProperty();
    }

    private static List<List<ReadOnlyObjectWrapper<CaseStatus>>> creePlateauVide() {
        List<List<ReadOnlyObjectWrapper<CaseStatus>>> plateau = new ArrayList<>(3);

        for (int iRangee = 0; iRangee < 3; iRangee++) {
            List<ReadOnlyObjectWrapper<CaseStatus>> rangee = new ArrayList<>(3);
            for (int iColonne = 0; iColonne < 3; iColonne++) {
                rangee.add(new ReadOnlyObjectWrapper<>(CaseStatus.VIDE));
            }

            plateau.add(rangee);
        }
        return plateau;
    }
}
