package tictactoe.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import tictactoe.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private GridPane plateauDeJeu;

    private final Jeu jeu;

    private final List<List<Case>> cases = new ArrayList<>();

    public MainController(Jeu jeu) {
        this.jeu = jeu;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int rangeeIndex = 0; rangeeIndex < 3; rangeeIndex++) {
            List<Case> rangee = new ArrayList<>(3);

            for (int colonne = 0; colonne < 3; colonne++) {
                Position position = new Position(rangeeIndex, colonne);

                rangee.add(new Case(position));

                rangee.get(colonne).statusProperty().bind(jeu.caseProperty(position));

                plateauDeJeu.add(rangee.get(colonne), colonne, rangeeIndex);
            }

            cases.add(rangee);
        }
    }

    public void setListener(CaseClickListener listener) {
        for (List<Case> rangee : cases) {
            for (Case boite : rangee) {
                boite.setListener(listener);
            }
        }
    }
}
