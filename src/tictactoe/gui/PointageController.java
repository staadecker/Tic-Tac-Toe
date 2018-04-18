package tictactoe.gui;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;
import tictactoe.Jeu;

@SuppressWarnings("WeakerAccess")
public class PointageController implements ChangeListener<Jeu.JeuStatus> {
    @FXML
    private Text textScoreCercle;
    @FXML
    private Text textScoreCroix;

    private final SimpleIntegerProperty scoreCercle = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty scoreCroix = new SimpleIntegerProperty(0);

    public void bindJeu(@NotNull ReadOnlyObjectProperty<Jeu.JeuStatus> jeuStatus) {
        jeuStatus.addListener(this);
    }

    @FXML
    public void initialize() {
        textScoreCercle.textProperty().bind(Bindings.convert(scoreCercle));
        textScoreCroix.textProperty().bind(Bindings.convert(scoreCroix));
    }

    @Override
    public void changed(ObservableValue<? extends Jeu.JeuStatus> observable, Jeu.JeuStatus oldValue, Jeu.JeuStatus newValue) {
        if (newValue == Jeu.JeuStatus.CERCLE_GAGNE){
            scoreCercle.set(scoreCercle.getValue() + 1);
        }

        if (newValue == Jeu.JeuStatus.CROIX_GAGNE){
            scoreCroix.set(scoreCroix.getValue() + 1);
        }
    }
}
