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
     * Le modèle de jeu
     */
    @NotNull
    private final Jeu jeu;

    @FXML
    private GridPane plateauDeJeu;

    /**
     * @param jeu le model de jeu
     */
    public MainController(@NotNull Jeu jeu) {
        this.jeu = jeu;
    }

    @FXML
    private void initialize() {
        //Ajouter chaque boite au grid pane
        for (Position position : new StructurePlateau<>()) {
            plateauDeJeu.add(new Boite((param) -> {
                        if (param == BoiteController.class) return new BoiteController(jeu);

                        else {
                            try {
                                return param.newInstance();
                            } catch (Exception exc) {
                                exc.printStackTrace();
                                throw new RuntimeException(exc);
                            }
                        }
                    }),
                    position.colonne, position.rangee);
        }
    }

    @FXML
    private void recommencer() {
        jeu.recommencer();
    }
}
