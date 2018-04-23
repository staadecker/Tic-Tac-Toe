package tictactoe.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.jetbrains.annotations.NotNull;
import tictactoe.Jeu;
import tictactoe.StatusJeu;

public class TextStatusController implements ChangeListener<StatusJeu> {
    @FXML
    private Label label;

    private final Jeu jeu;

    public TextStatusController(Jeu jeu) {
        this.jeu = jeu;

        jeu.jeuStatusProperty().addListener(this);
    }

    @FXML
    private void initialize(){
        updateStatus(jeu.jeuStatusProperty().get());
    }

    @Override
    public void changed(ObservableValue<? extends StatusJeu> observable, StatusJeu oldValue, StatusJeu newValue) {
        updateStatus(newValue);
    }

    private void updateStatus(@NotNull StatusJeu valeur) {
        switch (valeur) {
            case EN_PARTIE:
                label.setText(jeu.isTourAX() ? "Tour à X" : "Tour à O");
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
