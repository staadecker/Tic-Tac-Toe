package tictactoe.gui;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import tictactoe.*;
import tictactoe.util.Position;
import tictactoe.util.StructurePlateau;

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
            boite.statusProperty().bind(jeu.boiteStatusProperty(position));
            boite.setListener(jeu);
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

        pointageController.bindJeu(jeu.jeuStatusProperty()); //Attacher le controlleur de pointage au jeu
    }
}
