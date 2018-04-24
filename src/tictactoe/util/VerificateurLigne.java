package tictactoe.util;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.jetbrains.annotations.NotNull;
import tictactoe.StatusJeu;
import tictactoe.gui.BoiteController;

import java.util.List;

/**
 * Object qui vérifie un ligne pour des gagnants dans une ligne (par example des colonnes ou des rangée)
 */
class VerificateurLigne implements ChangeListener<BoiteController.Status> {

    private final ReadOnlyObjectWrapper<StatusJeu> status = new ReadOnlyObjectWrapper<>(StatusJeu.EN_PARTIE);

    //La liste de boites formant la ligne
    @NotNull
    private final List<ReadOnlyObjectProperty<BoiteController.Status>> ligne;

    VerificateurLigne(List<ReadOnlyObjectProperty<BoiteController.Status>> ligne) {
        this.ligne = ligne;

        //Se faire notifier si une des boites dans la ligne change
        for (ReadOnlyObjectProperty<BoiteController.Status> boite : ligne) {
            boite.addListener(this);
        }
    }

    /**
     * Appelé quand une des boites dans la ligne change
     */
    @Override
    public void changed(ObservableValue<? extends BoiteController.Status> observable, BoiteController.Status oldValue, BoiteController.Status newValue) {
        //Si il y a une boite de vide c'est indeterminé
        for (ReadOnlyObjectProperty<BoiteController.Status> boite : ligne) {
            if (boite.get() == BoiteController.Status.VIDE) {
                status.set(StatusJeu.EN_PARTIE);
                return;
            }
        }

        for (ReadOnlyObjectProperty<BoiteController.Status> boite : ligne) {
            if (newValue != boite.get()){
                status.set(StatusJeu.EGALITE);
                return;
            }
        }

        if (newValue == BoiteController.Status.CROIX){
            status.set(StatusJeu.X_GAGNE);
        } else {
            status.set(StatusJeu.O_GAGNE);
        }
    }

    ReadOnlyObjectProperty<StatusJeu> statusProperty() {
        return status.getReadOnlyProperty();
    }
}
