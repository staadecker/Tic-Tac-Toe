package tictactoe;

/**
 * Définie une position sur le plateau de jeu.
 * (0,0) est la case en haut à gauche
 */
public class Position {
    public final int rangee;
    public final int colonne;

    public Position(int rangee, int colonne) {
        this.rangee = rangee;
        this.colonne = colonne;
    }

    @Override
    public String toString() {
        return "rangee: " + rangee + " colonne: " + colonne;
    }
}
