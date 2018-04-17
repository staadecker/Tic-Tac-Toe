package tictactoe;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import tictactoe.gui.Boite;

import java.util.ArrayList;
import java.util.List;

public class Ligne implements ChangeListener<Boite.CaseStatus> {
    private List<ReadOnlyObjectProperty<Boite.CaseStatus>> ligne = new ArrayList<>();

    private final GagnantListener listener;

    Ligne(GagnantListener gagnantListener) {
        this.listener = gagnantListener;
    }

    void ajouter(ReadOnlyObjectProperty<Boite.CaseStatus> caseAAjouter){
        caseAAjouter.addListener(this);
        ligne.add(caseAAjouter);
    }

    @Override
    public void changed(ObservableValue<? extends Boite.CaseStatus> observable, Boite.CaseStatus oldValue, Boite.CaseStatus newValue) {
        if (newValue == Boite.CaseStatus.VIDE) return;

        for (ReadOnlyObjectProperty<Boite.CaseStatus> caseAVerifier : ligne) {
            if (caseAVerifier.get() != newValue) {
                return;
            }
        }

        if (newValue == Boite.CaseStatus.CROIX) {
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
