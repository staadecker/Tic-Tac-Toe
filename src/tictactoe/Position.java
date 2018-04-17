package tictactoe;

import org.jetbrains.annotations.Contract;

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

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Position)) return false;

        return rangee == ((Position) obj).rangee && colonne == ((Position) obj).colonne;
    }
}