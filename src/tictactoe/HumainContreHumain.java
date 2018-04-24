package tictactoe;

import tictactoe.util.Position;

/**
 * Définit un jeu humain contre humain
 * Quand une boite est appuyer cela signifie qu'elle est joué
 */
public class HumainContreHumain implements ClickListener {

    private final Jeu jeu;

    HumainContreHumain(Jeu jeu) {
        this.jeu = jeu;
    }

    @Override
    public void notifierBoiteClicked(Position position) {
        jeu.jouer(position);
    }
}