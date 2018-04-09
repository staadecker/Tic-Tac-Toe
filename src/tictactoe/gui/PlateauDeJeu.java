package tictactoe.gui;

import javafx.scene.layout.*;
import tictactoe.CaseClickListener;
import tictactoe.Joueur;
import tictactoe.Position;

public class PlateauDeJeu extends GridPane {

    private final Boite[][] boites = new Boite[3][3];

    public PlateauDeJeu() {
        super();

        for (int rangee = 0; rangee < boites.length; rangee++) {
            for (int colonne = 0; colonne < boites[rangee].length; colonne++) {
                boites[rangee][colonne] = new Boite(new Position(rangee, colonne));
                this.add(boites[rangee][colonne], colonne, rangee);
            }
        }

        appliquerConstraintes();
    }

    public void addListener(CaseClickListener listener) {
        for (Boite[] rangee : boites) {
            for (Boite boite : rangee) {
                boite.addListener(listener);
            }
        }
    }

    private void appliquerConstraintes() {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setFillWidth(true);
        columnConstraints.setHgrow(Priority.ALWAYS);
        this.getColumnConstraints().addAll(columnConstraints, columnConstraints, columnConstraints);


        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setFillHeight(true);
        rowConstraints.setVgrow(Priority.ALWAYS);
        this.getRowConstraints().addAll(rowConstraints, rowConstraints, rowConstraints);
    }

    public void setStatus(Position position, Joueur status) {
        boites[position.rangee][position.colonne].setStatus(status);
    }

    public Joueur[][] getStatus() {
        Joueur[][] status = new Joueur[boites.length][boites[0].length];

        for (int rangee = 0; rangee < status.length; rangee++) {
            for (int colonne = 0; colonne < boites[rangee].length; colonne++) {
                status[rangee][colonne] = boites[rangee][colonne].getStatus();
            }
        }

        return status;
    }
}