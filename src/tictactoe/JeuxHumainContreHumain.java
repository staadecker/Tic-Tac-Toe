package tictactoe;

import tictactoe.gui.PlateauDeJeu;

public class JeuxHumainContreHumain extends Jeux implements CaseClickListener {

    JeuxHumainContreHumain() {
        plateauDeJeu.addListener(this);
    }

    @Override
    public void notifierCaseClicked(Position position) {
        jouer(position);
    }
}