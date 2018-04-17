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

    private final StructurePlateau<Boite> boites = new StructurePlateau<>();

    public MainController(Jeu jeu) {
        for (Position position : boites) {
            Boite boite = new Boite(position);
            boite.statusProperty().bind(jeu.caseProperty(position));
            boites.set(position, boite);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Position position : boites) {
            plateauDeJeu.add(boites.get(position), position.colonne, position.rangee);
        }
    }

    public void setListener(Boite.ClickListener listener) {
        for (Position position : boites) {
            boites.get(position).setListener(listener);
        }
    }
}
