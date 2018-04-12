package tictactoe;

class Jeux {
    final MainController plateauDeJeu;

    Jeux(MainController plateauDeJeu) {
        this.plateauDeJeu = plateauDeJeu;
    }

    private Joueur tourA = Joueur.CROIX;

    public Joueur tourA() {
        return tourA;
    }

    public MainController getPlateauDeJeu() {
        return plateauDeJeu;
    }

    void jouer(Position position){
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
