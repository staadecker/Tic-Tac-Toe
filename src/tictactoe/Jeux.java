package tictactoe;

import tictactoe.gui.Boite;
import tictactoe.gui.PlateauDeJeu;

public class Jeux {
    final PlateauDeJeu plateauDeJeu = new PlateauDeJeu();

    private Joueur tourA = Joueur.CROIX;

    public Joueur tourA() {
        return tourA;
    }

    public PlateauDeJeu getPlateauDeJeu() {
        return plateauDeJeu;
    }

    public void jouer(Position position){
        plateauDeJeu.setStatus(position, tourA);

        changerTour();
    }

    private void changerTour(){
        if (tourA == Joueur.CROIX){
            tourA = Joueur.CERCLE;
        } else {
            tourA = Joueur.CROIX;
        }
    }
}
