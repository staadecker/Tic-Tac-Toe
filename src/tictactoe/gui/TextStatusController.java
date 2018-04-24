package tictactoe.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.jetbrains.annotations.NotNull;
import tictactoe.Jeu;
import tictactoe.StatusJeu;

public class TextStatusController  {
    @FXML
    private Label label;

    private final Jeu jeu;

    public TextStatusController(Jeu jeu) {
        this.jeu = jeu;

        jeu.jeuStatusProperty().addListener((observable, oldValue, newValue) -> updateStatus(jeu.jeuStatusProperty().get()));

        jeu.tourAXProperty().addListener((observable, oldValue, newValue) -> {
            if (jeu.jeuStatusProperty().get() == StatusJeu.EN_PARTIE){
                label.setText(newValue ? "Tour à X" : "Tour à O");
            }
        });
    }

    @FXML
    private void initialize(){
        updateStatus(jeu.jeuStatusProperty().get());
    }

    private void updateStatus(@NotNull StatusJeu valeur) {
        switch (valeur) {
            case EN_PARTIE:
                label.setText(jeu.tourAXProperty().get() ? "Tour à X" : "Tour à O");
                break;
            case O_GAGNE:
                label.setText("Cercle a gagné");
                break;
            case X_GAGNE:
                label.setText("Croix a gagné");
                break;
            case EGALITE:
                label.setText("Égalité");
                break;
        }
    }
}
