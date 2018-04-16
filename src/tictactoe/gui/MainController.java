package tictactoe.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import tictactoe.*;

import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
    @FXML
    private GridPane plateauDeJeu;

    private final StructurePlateau<Case> cases = new StructurePlateau<>();

    public MainController(Jeu jeu) {
        Iterator<Position> iterator = cases.iterator();

        while (iterator.hasNext()) {
            Position position = iterator.next();

            cases.set(position, new Case(position));

            cases.get(position).statusProperty().bind(jeu.caseProperty(position));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Iterator<Position> iterator = cases.iterator();

        while (iterator.hasNext()) {
            Position position = iterator.next();

            plateauDeJeu.add(cases.get(position), position.colonne, position.rangee);
        }
    }

    public void setListener(CaseClickListener listener) {
        Iterator<Position> iterator = cases.iterator();

        while (iterator.hasNext()) {
            cases.get(iterator.next()).setListener(listener);
        }
    }
}
