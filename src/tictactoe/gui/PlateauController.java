package tictactoe.gui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;
import tictactoe.Jeu;
import tictactoe.StatusJeu;
import tictactoe.util.Position;
import tictactoe.util.StructurePlateau;

public class PlateauController {
    /**
     * Liste de toutes les boites
     */
    @NotNull
    private final StructurePlateau<Boite> boites = new StructurePlateau<>();

    private final SimpleBooleanProperty shouldHighlight = new SimpleBooleanProperty(true);

    /**
     * Le tableau à remplir avec des boites
     */
    @FXML
    private GridPane plateauDeJeu;

    public PlateauController(Jeu jeu) {
        for (Position position : boites) {
            Boite boite = new Boite(position);
            boite.shouldHighlightProperty().bind(shouldHighlight);
            boite.attachListener(jeu);
            boite.statusProperty().bind(jeu.boiteStatusProperty(position));
            boites.set(position, boite); //Ajouter la boite à la liste
        }

        jeu.jeuStatusProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == StatusJeu.EN_PARTIE) {
                        shouldHighlight.set(true);
                    } else {
                        shouldHighlight.set(false);
                    }
                }
        );
    }

    @FXML
    private void initialize() {
        //Ajouter chaque boite au grid pane
        for (Position position : boites) {
            plateauDeJeu.add(boites.get(position), position.colonne, position.rangee);
        }
    }
}
