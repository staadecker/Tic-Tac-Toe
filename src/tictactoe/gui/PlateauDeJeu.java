package tictactoe.gui;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.Contract;
import tictactoe.CaseClickListener;
import tictactoe.Position;

public class PlateauDeJeu extends GridPane {

    private final Boite[][] boites = new Boite[3][3];

    public PlateauDeJeu(CaseClickListener listener) {
        super();

        for (int rangee = 0; rangee < boites.length; rangee++) {
            for (int colonne = 0; colonne < boites[rangee].length; colonne++) {
                boites[rangee][colonne] = new Boite(new Position(rangee, colonne), listener);
                this.add(boites[rangee][colonne], colonne, rangee);
            }
        }

        appliquerConstraintes();
    }

    private void appliquerConstraintes() {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(100.0 / boites[0].length);
        this.getColumnConstraints().addAll(columnConstraints, columnConstraints, columnConstraints);


        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(100.0 / boites.length);
        this.getRowConstraints().addAll(rowConstraints, rowConstraints, rowConstraints);
    }

    public void setStatus(Position position, Boite.Status status) {
        boites[position.rangee][position.colonne].setStatus(status);
    }

    public Boite.Status[][] getStatus() {
        Boite.Status[][] status = new Boite.Status[boites.length][boites[0].length];

        for (int rangee = 0; rangee < status.length; rangee++) {
            for (int colonne = 0; colonne < boites[rangee].length; colonne++) {
                status[rangee][colonne] = boites[rangee][colonne].getStatus();
            }
        }

        return status;
    }
}