package tictactoe.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Window;
import tictactoe.*;

import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
    @FXML
    private GridPane plateauDeJeu;

    @FXML
    private HBox pointage;

    @FXML
    private PointageController pointageController;

    private final StructurePlateau<Boite> boites = new StructurePlateau<>();

    private final Jeu jeu;

    public MainController(Jeu jeu) {
        this.jeu = jeu;

        for (Position position : boites) {
            Boite boite = new Boite(position);
            boite.statusProperty().bind(jeu.boiteProperty(position));
            boites.set(position, boite);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Position position : boites) {
            plateauDeJeu.add(boites.get(position), position.colonne, position.rangee);
        }

        pointageController.bindJeu(jeu.jeuProperty());
    }

    public void setListener(Boite.ClickListener listener) {
        for (Position position : boites) {
            boites.get(position).setListener(listener);
        }
    }
}
