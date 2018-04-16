package tictactoe;

public class JeuHumainContreHumain extends Jeu {

    @Override
    public void notifierCaseClicked(Position position) {
        jouer(position);
    }
}