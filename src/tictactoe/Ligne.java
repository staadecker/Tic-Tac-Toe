package tictactoe;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import tictactoe.gui.Boite;

import java.util.ArrayList;
import java.util.List;

public class Ligne implements ChangeListener<Boite.BoiteStatus> {
    private List<ReadOnlyObjectProperty<Boite.BoiteStatus>> ligne = new ArrayList<>();

    private final GagnantListener listener;

    Ligne(GagnantListener gagnantListener) {
        this.listener = gagnantListener;
    }

    void ajouter(ReadOnlyObjectProperty<Boite.BoiteStatus> caseAAjouter){
        caseAAjouter.addListener(this);
        ligne.add(caseAAjouter);
    }

    @Override
    public void changed(ObservableValue<? extends Boite.BoiteStatus> observable, Boite.BoiteStatus oldValue, Boite.BoiteStatus newValue) {
        if (newValue == Boite.BoiteStatus.VIDE) return;

        for (ReadOnlyObjectProperty<Boite.BoiteStatus> caseAVerifier : ligne) {
            if (caseAVerifier.get() != newValue) {
                return;
            }
        }

        if (newValue == Boite.BoiteStatus.CROIX) {
            listener.notifierGagnantX();
        } else {
            listener.notifierGagnantO();
        }
    }

    public interface GagnantListener {
        void notifierGagnantX();

        void notifierGagnantO();
    }
}
