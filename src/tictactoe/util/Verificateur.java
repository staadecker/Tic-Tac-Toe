package tictactoe.util;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import tictactoe.gui.Boite;

import java.util.Iterator;
import java.util.List;

public class Verificateur implements ChangeListener<Boite.BoiteStatus> {
    public interface GagnantListener {
        void notifierGagnantX();
        void notifierGagnantO();
        void notifierEgalite();
    }

    private final int taille;
    private final GagnantListener listener;

    private int boiteNonVide = 0;

    public Verificateur(GagnantListener listener, StructurePlateau<ReadOnlyObjectProperty<Boite.BoiteStatus>> statusBoite) {
        this.taille = statusBoite.size();
        this.listener = listener;

        Iterator<List<ReadOnlyObjectProperty<Boite.BoiteStatus>>> iteratorRangee = statusBoite.iteratorRangee();

        while (iteratorRangee.hasNext()){
            new Ligne(listener, iteratorRangee.next());
        }

        Iterator<List<ReadOnlyObjectProperty<Boite.BoiteStatus>>> iteratorColonne = statusBoite.iteratorColonne();

        while (iteratorColonne.hasNext()){
            new Ligne(listener, iteratorColonne.next());
        }

        new Ligne(listener, statusBoite.getDiagonaleGaucheDroit());
        new Ligne(listener, statusBoite.getDiagonaleDroiteGauche());

        for (Position position : statusBoite){
            statusBoite.get(position).addListener(this);
        }
    }

    @Override
    public void changed(ObservableValue<? extends Boite.BoiteStatus> observable, Boite.BoiteStatus oldValue, Boite.BoiteStatus newValue) {
        if (oldValue == Boite.BoiteStatus.VIDE){
            boiteNonVide++;
        }

        if (newValue == Boite.BoiteStatus.VIDE){
            boiteNonVide--;
        }

        if (boiteNonVide == taille){
            listener.notifierEgalite();
        }
    }
}
