package tictactoe;

import tictactoe.gui.MainController;

public class JeuHumainContreHumain extends Jeu implements CaseClickListener {

    public JeuHumainContreHumain(MainController plateauDeJeu) {
        super();
        plateauDeJeu.setListener(this);
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