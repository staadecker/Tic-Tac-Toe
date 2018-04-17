package tictactoe;

import tictactoe.gui.Boite;

public class JeuHumainContreHumain extends Jeu implements Boite.ClickListener {
    @Override
    public void notifierCaseClicked(Position position) {
        jouer(position);
    }
}