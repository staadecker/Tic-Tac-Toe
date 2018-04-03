package tictactoe;

import tictactoe.gui.PlateauDeJeu;

public class Jeux implements CaseClickListener {

    private final Joueur cercle;
    private final Joueur croix;

    private final PlateauDeJeu plateauDeJeu = new PlateauDeJeu(this);

    private boolean tourACroix = true;

    public Jeux(Joueur cercle, Joueur croix) {
        this.cercle = cercle;
        this.croix = croix;
    }

    @Override
    public void notifierCaseClicked(Position position) {
        if (tourACroix) {
            croix.notifierCaseClick(position);
        } else {
            cercle.notifierCaseClick(position);
        }
    }

    public PlateauDeJeu getPlateauDeJeu() {
        return plateauDeJeu;
    }
}
