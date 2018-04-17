package tictactoe;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import org.jetbrains.annotations.NotNull;
import tictactoe.gui.Boite;

import java.util.Iterator;
import java.util.List;

public abstract class Jeu implements Ligne.GagnantListener, Boite.ClickListener {
    private final StructurePlateau<ReadOnlyObjectWrapper<Boite.CaseStatus>> statusCases = creeCasesVide();

    private final ReadOnlyObjectWrapper<JeuStatus> statusJeu = new ReadOnlyObjectWrapper<>(JeuStatus.TOUR_CROIX);

    public Jeu() {
        Iterator<List<ReadOnlyObjectWrapper<Boite.CaseStatus>>> iteratorRangee = statusCases.iteratorRangee();

        while (iteratorRangee.hasNext()){
            creeLigne(iteratorRangee.next());
        }

        Iterator<List<ReadOnlyObjectWrapper<Boite.CaseStatus>>> iteratorColonne = statusCases.iteratorColonne();

        while (iteratorColonne.hasNext()){
            creeLigne(iteratorColonne.next());
        }

        creeLigne(statusCases.getDiagonaleGaucheDroit());
        creeLigne(statusCases.getDiagonaleDroiteGauche());
    }

    private void creeLigne(@NotNull List<ReadOnlyObjectWrapper<Boite.CaseStatus>> liste){
        Ligne ligne = new Ligne(this);

        for (ReadOnlyObjectWrapper<Boite.CaseStatus> caseDansLaLigne : liste){
            ligne.ajouter(caseDansLaLigne.getReadOnlyProperty());
        }
    }

    public ReadOnlyObjectProperty<JeuStatus> jeuProperty(){
        return statusJeu.getReadOnlyProperty();
    }

    public ReadOnlyObjectProperty<Boite.CaseStatus> caseProperty(Position position) {
        return statusCases.get(position).getReadOnlyProperty();
    }

    void jouer(Position position) {
        ReadOnlyObjectWrapper<Boite.CaseStatus> caseAJouer = statusCases.get(position);

        if (statusJeu.get() == JeuStatus.TOUR_CROIX) {
            caseAJouer.set(Boite.CaseStatus.CROIX);
            statusJeu.set(JeuStatus.TOUR_CERCLE);
        } else if (statusJeu.get() == JeuStatus.TOUR_CERCLE) {
            caseAJouer.set(Boite.CaseStatus.CERCLE);
            statusJeu.set(JeuStatus.TOUR_CROIX);
        }
    }

    @Override
    public void notifierGagnantX() {
        System.out.println("X gagnant");
        statusJeu.set(JeuStatus.CROIX_GAGNE);
    }

    @Override
    public void notifierGagnantO() {
        System.out.println("O gagnant");
        statusJeu.set(JeuStatus.CERCLE_GAGNE);
    }

    private static StructurePlateau<ReadOnlyObjectWrapper<Boite.CaseStatus>> creeCasesVide() {
        StructurePlateau<ReadOnlyObjectWrapper<Boite.CaseStatus>> data = new StructurePlateau<>();

        for (Position position : data) {
            data.set(position, new ReadOnlyObjectWrapper<>(Boite.CaseStatus.VIDE));
        }

        return data;
    }

    public enum JeuStatus {
        TOUR_CROIX,
        TOUR_CERCLE,
        EGALITE,
        CROIX_GAGNE,
        CERCLE_GAGNE
    }
}