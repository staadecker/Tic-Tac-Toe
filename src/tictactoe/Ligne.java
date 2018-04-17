package tictactoe;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.List;

public class Ligne implements ChangeListener<CaseStatus> {

    private final List<ReadOnlyObjectProperty<CaseStatus>> ligne;

    private final LigneListener listener;

    public Ligne(List<ReadOnlyObjectProperty<CaseStatus>> cases, LigneListener listener) {
        this.listener = listener;
        this.ligne = cases;

        for (ReadOnlyObjectProperty<CaseStatus> uneCase : ligne) {
            uneCase.addListener(this);
        }
    }

    @Override
    public void changed(ObservableValue<? extends CaseStatus> observable, CaseStatus oldValue, CaseStatus newValue) {
        if (newValue == CaseStatus.VIDE) return;

        for (ReadOnlyObjectProperty<CaseStatus> caseAVerifier : ligne) {
            if (caseAVerifier.get() != newValue) {
                return;
            }
        }

        if (newValue == CaseStatus.CROIX) {
            listener.notifierGagnantX();
        } else {
            listener.notifierGagnantO();
        }
    }

    public interface LigneListener {
        void notifierGagnantX();

        void notifierGagnantO();
    }
}
