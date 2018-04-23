package tictactoe.gui;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.jetbrains.annotations.NotNull;
import tictactoe.Jeu;

public class TextStatusController implements ChangeListener<Jeu.JeuStatus> {
    @FXML
    private Label label;

    void attacherJeu(ReadOnlyObjectProperty<Jeu.JeuStatus> jeuStatus){
        jeuStatus.addListener(this);
        updateStatus(jeuStatus.get());
    }

    @Override
    public void changed(ObservableValue<? extends Jeu.JeuStatus> observable, Jeu.JeuStatus oldValue, Jeu.JeuStatus newValue) {
        updateStatus(newValue);
    }

    private void updateStatus(@NotNull Jeu.JeuStatus valeur) {
        switch (valeur) {
            case TOUR_CERCLE:
                label.setText("Tour à O");
                break;
            case TOUR_CROIX:
                label.setText("Tour à X");
                break;
            case CERCLE_GAGNE:
                label.setText("Cercle a gagné");
                break;
            case CROIX_GAGNE:
                label.setText("Croix a gagné");
                break;
            case EGALITE:
                label.setText("Égalité");
                break;
        }
    }
}
