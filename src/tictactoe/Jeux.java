package tictactoe;

public class Jeux {
    final PlateauDeJeu plateauDeJeu;

    public Jeux(PlateauDeJeu plateauDeJeu) {
        this.plateauDeJeu = plateauDeJeu;
    }

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
