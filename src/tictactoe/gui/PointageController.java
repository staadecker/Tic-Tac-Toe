package tictactoe.gui;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;
import tictactoe.Jeu;

/**
 * Controlle la zone qui montre les points
 */
@SuppressWarnings("WeakerAccess")
public class PointageController {
    @FXML
    private Text textScoreCercle;
    @FXML
    private Text textScoreCroix;

    private final SimpleIntegerProperty scoreCercle = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty scoreCroix = new SimpleIntegerProperty(0);

    public void bindJeu(@NotNull ReadOnlyObjectProperty<Jeu.JeuStatus> jeuStatus) {
        jeuStatus.addListener(
                (observable, oldValue, newValue) -> {
                    switch (newValue) {
                        case CROIX_GAGNE:
                            scoreCroix.set(scoreCroix.getValue() + 1);
                            break;
                        case CERCLE_GAGNE:
                            scoreCercle.set(scoreCercle.getValue() + 1);
                            break;
                    }
                }
        );
    }

    @FXML
    public void initialize() {
        textScoreCercle.textProperty().bind(Bindings.convert(scoreCercle));
        textScoreCroix.textProperty().bind(Bindings.convert(scoreCroix));
    }
}
