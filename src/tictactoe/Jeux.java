package tictactoe;

import tictactoe.gui.Boite;
import tictactoe.gui.PlateauDeJeu;

public class Jeux {
    final PlateauDeJeu plateauDeJeu = new PlateauDeJeu();

    private boolean isTourCroix = true;

    public boolean isTourCroix() {
        return isTourCroix;
    }

    public PlateauDeJeu getPlateauDeJeu() {
        return plateauDeJeu;
    }

    public void jouer(Position position){
        if (isTourCroix) {
            plateauDeJeu.setStatus(position, Boite.Status.CROIX);
        } else {
            plateauDeJeu.setStatus(position, Boite.Status.CERCLE);
        }

        isTourCroix = !isTourCroix;
    }
}
