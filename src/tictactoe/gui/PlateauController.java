package tictactoe.gui;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;
import tictactoe.Jeu;
import tictactoe.util.Position;
import tictactoe.util.StructurePlateau;

public class PlateauController {
    /**
     * Liste de toutes les boites
     */
    @NotNull
    private final StructurePlateau<Boite> boites = new StructurePlateau<>();

    /**
     * Le tableau à remplir avec des boites
     */
    @FXML
    private GridPane plateauDeJeu;

    public PlateauController() {
        for (Position position : boites) {
            boites.set(position, new Boite(position)); //Ajouter la boite à la liste
        }
    }

    @FXML
    private void initialize(){
        //Ajouter chaque boite au grid pane
        for (Position position : boites) {
            plateauDeJeu.add(boites.get(position), position.colonne, position.rangee);
        }
    }

    void attacherJeu(@NotNull Jeu jeu){
        for (Position position: boites){
            boites.get(position).attachListener(jeu);
            boites.get(position).statusProperty().bind(jeu.boiteStatusProperty(position)); //Attacher la boite au model pour que si le model change la boite change avec
        }
    }
}
