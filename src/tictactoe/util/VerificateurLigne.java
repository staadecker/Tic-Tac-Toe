package tictactoe.util;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.jetbrains.annotations.NotNull;
import tictactoe.gui.Boite;

import java.util.List;

/**
 * Object qui vérifie un ligne pour des gagnants dans une ligne (par example des colonnes ou des rangée)
 */
class VerificateurLigne implements ChangeListener<Boite.Status> {
    enum Status {
        X_GAGNE,
        O_GAGNE,
        EGALITE,
        INDETERMINE
    }

    private final ReadOnlyObjectWrapper<Status> status = new ReadOnlyObjectWrapper<>(Status.INDETERMINE);

    //La liste de boites formant la ligne
    @NotNull
    private final List<ReadOnlyObjectProperty<Boite.Status>> ligne;

    VerificateurLigne(List<ReadOnlyObjectProperty<Boite.Status>> ligne) {
        this.ligne = ligne;

        //Se faire notifier si une des boites dans la ligne change
        for (ReadOnlyObjectProperty<Boite.Status> boite : ligne) {
            boite.addListener(this);
        }
    }

    /**
     * Appelé quand une des boites dans la ligne change
     */
    @Override
    public void changed(ObservableValue<? extends Boite.Status> observable, Boite.Status oldValue, Boite.Status newValue) {
        //Si il y a une boite de vide c'est indeterminé
        for (ReadOnlyObjectProperty<Boite.Status> boite : ligne) {
            if (boite.get() == Boite.Status.VIDE) {
                status.set(Status.INDETERMINE);
                return;
            }
        }

        for (ReadOnlyObjectProperty<Boite.Status> boite : ligne) {
            if (newValue != boite.get()){
                status.set(Status.EGALITE);
                return;
            }
        }

        if (newValue == Boite.Status.CROIX){
            status.set(Status.X_GAGNE);
        } else {
            status.set(Status.O_GAGNE);
        }
    }

    ReadOnlyObjectProperty<Status> statusProperty() {
        return status.getReadOnlyProperty();
    }
}
