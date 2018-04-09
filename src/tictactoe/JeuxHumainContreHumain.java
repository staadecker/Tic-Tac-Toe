package tictactoe;

import tictactoe.gui.PlateauDeJeu;

public class JeuxHumainContreHumain extends Jeux implements CaseClickListener {

    JeuxHumainContreHumain() {
        plateauDeJeu.addListener(this);
    }

    @Override
    public void notifierCaseClicked(Position position) {
        jouer(position);
        Joueur gagnant = Algorithm.getGagnant(plateauDeJeu.getStatus());

        if (gagnant != null){
            System.out.println(gagnant.toString());
        }
    }
}