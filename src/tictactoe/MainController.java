package tictactoe;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private GridPane plateauDeJeu;

    private final Boite[][] boites = new Boite[3][3];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int rangee = 0; rangee < boites.length; rangee++) {
            for (int colonne = 0; colonne < boites[rangee].length; colonne++) {
                boites[rangee][colonne] = new Boite(new Position(rangee, colonne));
                plateauDeJeu.add(boites[rangee][colonne], colonne, rangee);
            }
        }

        JeuxHumainContreHumain jeux = new JeuxHumainContreHumain(this);
    }

    void setListener(CaseClickListener listener) {
        for (Boite[] rangee : boites) {
            for (Boite boite : rangee) {
                boite.setListener(listener);
            }
        }
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
