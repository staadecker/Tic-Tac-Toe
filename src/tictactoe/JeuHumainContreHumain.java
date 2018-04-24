package tictactoe;

import tictactoe.util.Position;

/**
 * Définit un jeu humain contre humain
 * Quand une boite est appuyer cela signifie qu'elle est joué
 */
public class JeuHumainContreHumain extends Jeu implements ClickListener {
    @Override
    public void notifierBoiteClicked(Position position) {
        jouer(position);
    }
}