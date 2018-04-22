package tictactoe.gui;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;
import tictactoe.Jeu;
import tictactoe.util.Position;
import tictactoe.util.StructurePlateau;

/**
 * Class qui controlle l'interface graphique du jeu
 */
public class MainController {
    /**
     * Le tableau à remplir avec des boites
     */
    @FXML
    private GridPane plateauDeJeu;

    /**
     * Le controller de la zone de pointage
     */
    @FXML
    private PointageController pointageController;

    /**
     * Liste de toutes les boites
     */
    @NotNull
    private final StructurePlateau<Boite> boites = new StructurePlateau<>();

    /**
     * Le modèle de jeu
     */
    @NotNull
    private final Jeu jeu;

    /**
     * @param jeu le model de jeu
     */
    public MainController(@NotNull Jeu jeu) {
        this.jeu = jeu;

        for (Position position : boites) {
            Boite boite = new Boite(position, jeu);
            boite.statusProperty().bind(jeu.boiteStatusProperty(position)); //Attacher la boite au model pour que si le model change la boite change avec
            boites.set(position, boite); //Ajouter la boite à la liste
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
