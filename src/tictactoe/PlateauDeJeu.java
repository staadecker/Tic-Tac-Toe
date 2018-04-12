package tictactoe;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;

import java.io.IOException;

public class PlateauDeJeu extends GridPane {

    private final Boite[][] boites = new Boite[3][3];

    public PlateauDeJeu() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/plateau.fxml"));
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        for (int rangee = 0; rangee < boites.length; rangee++) {
            for (int colonne = 0; colonne < boites[rangee].length; colonne++) {
                boites[rangee][colonne] = new Boite(new Position(rangee, colonne));
                this.add(boites[rangee][colonne], colonne, rangee);
            }
        }

        appliquerConstraintes();
    }

    void setListener(CaseClickListener listener) {
        for (Boite[] rangee : boites) {
            for (Boite boite : rangee) {
                boite.setListener(listener);
            }
        }
    }

    private void appliquerConstraintes() {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        this.getColumnConstraints().addAll(columnConstraints, columnConstraints, columnConstraints);


        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.ALWAYS);
        this.getRowConstraints().addAll(rowConstraints, rowConstraints, rowConstraints);
    }

    void setStatus(Position position, Joueur status) {
        boites[position.rangee][position.colonne].setStatus(status);
    }

    Joueur[][] getStatus() {
        Joueur[][] status = new Joueur[boites.length][boites[0].length];

        for (int rangee = 0; rangee < status.length; rangee++) {
            for (int colonne = 0; colonne < boites[rangee].length; colonne++) {
                status[rangee][colonne] = boites[rangee][colonne].getStatus();
            }
        }

        return status;
    }
}