package tictactoe;

class Position {
    final int rangee;
    final int colonne;

    Position(int rangee, int colonne) {
        this.rangee = rangee;
        this.colonne = colonne;
    }

    @Override
    public String toString() {
        return "rangee: " + rangee + " colonne: " + colonne;
    }
}
