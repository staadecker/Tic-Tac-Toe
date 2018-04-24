package tictactoe.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;
import tictactoe.ClickListener;
import tictactoe.Jeu;
import tictactoe.util.Position;
import tictactoe.util.StructurePlateau;

import java.io.IOException;

/**
 * Class qui controlle l'interface graphique du jeu
 */
public class MainController {
    /**
     * Le modèle de jeu
     */
    @NotNull
    private final Jeu jeu;

    private final ClickListener listener;

    @FXML
    private GridPane plateauDeJeu;

    /**
     * @param jeu le model de jeu
     */
    public MainController(@NotNull Jeu jeu, ClickListener listener) {
        this.jeu = jeu;
        this.listener = listener;
    }

    @FXML
    private void initialize() {
        //Ajouter chaque boite au grid pane
        for (Position position : new StructurePlateau<>()) {

            //Attacher l'objet au ficihier fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/boite.fxml"));
            fxmlLoader.setController(new BoiteController(jeu, listener, position));

            try {
                plateauDeJeu.add(fxmlLoader.load(), position.colonne, position.rangee);
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }

        }
    }

    @FXML
    private void recommencer() {
        jeu.recommencer();
    }
}
