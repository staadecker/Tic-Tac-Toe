package tictactoe;

import tictactoe.gui.Boite;
import tictactoe.gui.PlateauDeJeu;

public class Jeux implements CaseClickListener {

    private final Joueur cercle;
    private final Joueur croix;

    private final PlateauDeJeu plateauDeJeu = new PlateauDeJeu(this);

    private boolean tourACroix = true;

    Jeux(Joueur cercle, Joueur croix) {
        this.cercle = cercle;
        this.croix = croix;
    }

    @Override
    public void notifierCaseClicked(Position position) {
        if (tourACroix) {
            jouer(croix.notifierCaseClick(position), Boite.Status.CROIX);
        } else {
            jouer(cercle.notifierCaseClick(position), Boite.Status.CERCLE);
        }
    }

    private void jouer(Position emplacement, Boite.Status joueur) {
        if (emplacement != null) {
            plateauDeJeu.setStatus(emplacement, joueur);
            tourACroix = !tourACroix;
        }
    }

    PlateauDeJeu getPlateauDeJeu() {
        return plateauDeJeu;
    }
}
