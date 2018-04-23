package tictactoe.gui;

import javafx.fxml.FXML;
import org.jetbrains.annotations.NotNull;
import tictactoe.Jeu;

/**
 * Class qui controlle l'interface graphique du jeu
 */
public class MainController {
    /**
     * Le controller de la zone de pointage
     */
    @FXML
    private PointageController pointageController;

    @FXML
    private TextStatusController textStatusController;

    @FXML
    private PlateauController plateauController;

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

    /**
     * Appeler après que le FXML est chargé
     */
    @FXML
    public void initialize() {
        pointageController.attacherStatusJeu(jeu.jeuStatusProperty()); //Attacher le controlleur de pointage au jeu
        textStatusController.attacherStatusJeu(jeu.jeuStatusProperty());
        plateauController.attacherJeu(jeu);
    }

    @FXML
    private void recommencer(){
        jeu.recommencer();
    }
}
