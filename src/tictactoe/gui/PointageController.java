package tictactoe.gui;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;
import tictactoe.JeuBase;

/**
 * Controlle la zone qui montre les points
 */
public class PointageController {
    @FXML
    private Text textScoreCercle;
    @FXML
    private Text textScoreCroix;

    private final SimpleIntegerProperty scoreCercle = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty scoreCroix = new SimpleIntegerProperty(0);

    @FXML
    public void initialize() {
        //Attacher la boite de texte avec le score propriété de score
        //Bindings.convert() car la propriété est un IntegerProperty et le text est un StringProperty
        textScoreCercle.textProperty().bind(Bindings.convert(scoreCercle));
        textScoreCroix.textProperty().bind(Bindings.convert(scoreCroix));
    }

    /**
     * Attacher le status de jeu au controller de pointage
     *
     * @param jeuStatus la propriété du status de jeu
     */
    void bindJeu(@NotNull ReadOnlyObjectProperty<JeuBase.JeuStatus> jeuStatus) {
        //Si la propriété de status de jeu change...
        jeuStatus.addListener(
                (observable, oldValue, newValue) -> {
                    switch (newValue) {
                        //Si les X gagne, +1 pour les X
                        case CROIX_GAGNE:
                            scoreCroix.set(scoreCroix.getValue() + 1);
                            break;
                        //Si les O gagne +1 pour les O
                        case CERCLE_GAGNE:
                            scoreCercle.set(scoreCercle.getValue() + 1);
                            break;
                    }
                }
        );
    }
}
