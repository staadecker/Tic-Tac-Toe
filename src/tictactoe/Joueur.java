package tictactoe;

import tictactoe.gui.Boite;

public interface Joueur {
    Position notifierCaseClick(Position position);
    Position notifierTour(Boite.Status[][] statusDuPlateau);
}
