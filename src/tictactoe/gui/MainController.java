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

public class MainController {
    /**
     * Le tableau à remplir avec des cases
     */
    @FXML
    private GridPane plateauDeJeu;

    /**
     * Le controller de la zone de pointage
     */
    @FXML
    private PointageController pointageController;

    /**
     * Liste de boites
     */
    private final StructurePlateau<Boite> boites = new StructurePlateau<>();

    /**
     * Le jeu
     */
    private final Jeu jeu;

    public MainController(Jeu jeu) {
        this.jeu = jeu;

        for (Position position : boites) {
            Boite boite = new Boite(position);
            boite.statusProperty().bind(jeu.boiteProperty(position));
            boites.set(position, boite);
        }
    }

    /**
     * Appeler après que le FXML est chargé
     */
    @FXML
    public void initialize() {
        //Ajouter chaque boite au grid pane
        for (Position position : boites) {
            plateauDeJeu.add(boites.get(position), position.colonne, position.rangee);
        }

        pointageController.bindJeu(jeu.jeuProperty()); //Attacher le controlleur de pointage au jeu
    }

    public void setListener(Boite.ClickListener listener) {
        for (Position position : boites) {
            boites.get(position).setListener(listener);
        }
    }
}
