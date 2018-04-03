package tictactoe;

import tictactoe.gui.Boite;

public class JoueurHumain implements Joueur {
    @Override
    public Position notifierCaseClick(Position position) {
        return position;
    }

    @Override
    public Position notifierTour(Boite.Status[][] statusDuPlateau) {
        return null;
    }
}
