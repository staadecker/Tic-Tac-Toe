package tictactoe.util;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import tictactoe.gui.Boite;

import java.util.ArrayList;
import java.util.List;

public class Ligne implements ChangeListener<Boite.BoiteStatus> {
    private final List<ReadOnlyObjectProperty<Boite.BoiteStatus>> ligne;

    private final Verificateur.GagnantListener listener;

    Ligne(Verificateur.GagnantListener gagnantListener) {
        this(gagnantListener, new ArrayList<>());
    }

    Ligne(Verificateur.GagnantListener gagnantListener, List<ReadOnlyObjectProperty<Boite.BoiteStatus>> ligne){
        this.listener = gagnantListener;
        this.ligne = ligne;

        for (ReadOnlyObjectProperty<Boite.BoiteStatus> boite : ligne){
            boite.addListener(this);
        }
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

}
