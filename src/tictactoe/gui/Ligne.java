package tictactoe.gui;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import tictactoe.CaseStatus;
import tictactoe.StructurePlateau;

import java.util.ArrayList;
import java.util.List;

public class Ligne implements ChangeListener<CaseStatus> {
    private final List<ReadOnlyObjectProperty<CaseStatus>> ligne = new ArrayList<>(StructurePlateau.GRANDEUR);

    private final LigneListener listener;

    public Ligne(LigneListener listener) {
        this.listener = listener;
    }

    public void ajouter(ReadOnlyObjectProperty<CaseStatus> caseAAjouter){
        ligne.add(caseAAjouter);
        caseAAjouter.addListener(this);
    }

    @Override
    public void changed(ObservableValue<? extends CaseStatus> observable, CaseStatus oldValue, CaseStatus newValue) {
        for (ReadOnlyObjectProperty<CaseStatus> caseAVerifier : ligne){
            if (caseAVerifier.get() != newValue){
                return;
            }
        }

        if (newValue == CaseStatus.CROIX){
            listener.notifierGagnantX();
        } else {
            listener.notifierGagnantO();
        }
    }

    public interface LigneListener{
        void notifierGagnantX();
        void notifierGagnantO();
    }
}
