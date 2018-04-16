package tictactoe;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

import java.util.Iterator;

public abstract class Jeu implements CaseClickListener{
    private final StructurePlateau<ReadOnlyObjectWrapper<CaseStatus>> statusCase = creeStructureTableau();

    private final ReadOnlyObjectWrapper<JeuStatus> statusJeu = new ReadOnlyObjectWrapper<>(JeuStatus.TOUR_CROIX);

    public ReadOnlyObjectProperty<JeuStatus> jeuProperty(){
        return statusJeu.getReadOnlyProperty();
    }

    public ReadOnlyObjectProperty<CaseStatus> caseProperty(Position position) {
        return statusCase.get(position).getReadOnlyProperty();
    }

    void jouer(Position position) {
        ReadOnlyObjectWrapper<CaseStatus> caseAJouer = statusCase.get(position);

        if (statusJeu.get() == JeuStatus.TOUR_CROIX) {
            caseAJouer.set(CaseStatus.CROIX);
            statusJeu.set(JeuStatus.TOUR_CERCLE);
        } else if (statusJeu.get() == JeuStatus.TOUR_CERCLE) {
            caseAJouer.set(CaseStatus.CERCLE);
            statusJeu.set(JeuStatus.TOUR_CROIX);
        }
    }

    private static StructurePlateau<ReadOnlyObjectWrapper<CaseStatus>> creeStructureTableau() {
        StructurePlateau<ReadOnlyObjectWrapper<CaseStatus>> data = new StructurePlateau<>();

        Iterator<Position> iterator = data.iterator();

        while (iterator.hasNext()) {
            data.set(iterator.next(), new ReadOnlyObjectWrapper<>(CaseStatus.VIDE));
        }

        return data;
    }
}