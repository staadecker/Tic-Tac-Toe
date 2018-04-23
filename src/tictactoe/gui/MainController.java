package tictactoe.gui;

import javafx.fxml.FXML;
import org.jetbrains.annotations.NotNull;
import tictactoe.Jeu;

/**
 * Class qui controlle l'interface graphique du jeu
 */
public class MainController {
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
    }

    @FXML
    private void recommencer(){
        jeu.recommencer();
    }
}
