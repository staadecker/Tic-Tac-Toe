package tictactoe.gui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlateauDeJeuTest {

    @Test
    void getRangee() {
        Assertions.assertEquals(PlateauDeJeu.getRangee(3),1);
        Assertions.assertEquals(PlateauDeJeu.getRangee(7),2);
    }

    @Test
    void getColonne() {
        Assertions.assertEquals(PlateauDeJeu.getColonne(2), 2);
        Assertions.assertEquals(PlateauDeJeu.getColonne(6), 0);
    }
}