package tictactoe;

import tictactoe.gui.MainController;

public class JeuxHumainContreHumain extends Jeux implements CaseClickListener {

    public JeuxHumainContreHumain(MainController plateauDeJeu) {
        super(plateauDeJeu);
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