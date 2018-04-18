package tictactoe;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import org.jetbrains.annotations.NotNull;
import tictactoe.gui.Boite;

import java.util.Iterator;
import java.util.List;

public abstract class Jeu implements Ligne.GagnantListener, Boite.ClickListener {
    private final StructurePlateau<ReadOnlyObjectWrapper<Boite.BoiteStatus>> statusBoite = creeCasesVide();

    private final ReadOnlyObjectWrapper<JeuStatus> statusJeu = new ReadOnlyObjectWrapper<>(JeuStatus.TOUR_CROIX);

    public Jeu() {
        Iterator<List<ReadOnlyObjectWrapper<Boite.BoiteStatus>>> iteratorRangee = statusBoite.iteratorRangee();

        while (iteratorRangee.hasNext()){
            creeLigne(iteratorRangee.next());
        }

        Iterator<List<ReadOnlyObjectWrapper<Boite.BoiteStatus>>> iteratorColonne = statusBoite.iteratorColonne();

        while (iteratorColonne.hasNext()){
            creeLigne(iteratorColonne.next());
        }

        creeLigne(statusBoite.getDiagonaleGaucheDroit());
        creeLigne(statusBoite.getDiagonaleDroiteGauche());
    }

    private void creeLigne(@NotNull List<ReadOnlyObjectWrapper<Boite.BoiteStatus>> liste){
        Ligne ligne = new Ligne(this);

        for (ReadOnlyObjectWrapper<Boite.BoiteStatus> caseDansLaLigne : liste){
            ligne.ajouter(caseDansLaLigne.getReadOnlyProperty());
        }
    }

    public ReadOnlyObjectProperty<JeuStatus> jeuProperty(){
        return statusJeu.getReadOnlyProperty();
    }

    public ReadOnlyObjectProperty<Boite.BoiteStatus> boiteProperty(Position position) {
        return statusBoite.get(position).getReadOnlyProperty();
    }

    void jouer(Position position) {
        ReadOnlyObjectWrapper<Boite.BoiteStatus> boiteProperty = statusBoite.get(position);

        if (statusJeu.get() == JeuStatus.TOUR_CROIX) {
            boiteProperty.set(Boite.BoiteStatus.CROIX);
            statusJeu.set(JeuStatus.TOUR_CERCLE);
        } else if (statusJeu.get() == JeuStatus.TOUR_CERCLE) {
            boiteProperty.set(Boite.BoiteStatus.CERCLE);
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

    private static StructurePlateau<ReadOnlyObjectWrapper<Boite.BoiteStatus>> creeCasesVide() {
        StructurePlateau<ReadOnlyObjectWrapper<Boite.BoiteStatus>> data = new StructurePlateau<>();

        for (Position position : data) {
            data.set(position, new ReadOnlyObjectWrapper<>(Boite.BoiteStatus.VIDE));
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