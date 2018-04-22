package tictactoe;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import org.jetbrains.annotations.NotNull;
import tictactoe.gui.Boite;
import tictactoe.util.Position;
import tictactoe.util.StructurePlateau;
import tictactoe.util.Verificateur;

/**
 * La base d'un jeu. Défini tout sauf comment et quand les joueurs vont jouer
 */
public abstract class JeuBase implements Jeu, Verificateur.GagnantListener {
    private final StructurePlateau<ReadOnlyObjectWrapper<Boite.BoiteStatus>> statusBoite = creeCasesVide();

    /**
     * Les X commence toujours
     */
    private final ReadOnlyObjectWrapper<JeuStatus> statusJeu = new ReadOnlyObjectWrapper<>(JeuStatus.TOUR_CROIX);

    JeuBase() {
        new Verificateur(this, creeReadOnlyStatusBoite(statusBoite));
    }

    public void jouer(Position position) {
        ReadOnlyObjectWrapper<Boite.BoiteStatus> boiteProperty = statusBoite.get(position);

        switch (statusJeu.get()) {
            case TOUR_CROIX:
                boiteProperty.set(Boite.BoiteStatus.CROIX);
                break;
            case TOUR_CERCLE:
                boiteProperty.set(Boite.BoiteStatus.CERCLE);
                break;
            default:
                throw new RuntimeException("Ne peut pas jouer");
        }

        switch (statusJeu.get()) {
            case EGALITE:
            case CROIX_GAGNE:
            case CERCLE_GAGNE:
                System.out.println(statusJeu.get().toString());
                break;
            case TOUR_CERCLE:
                statusJeu.set(JeuStatus.TOUR_CROIX);
                break;
            case TOUR_CROIX:
                statusJeu.set(JeuStatus.TOUR_CERCLE);
                break;
        }
    }

    @Override
    public void notifierGagnantX() {
        statusJeu.set(JeuStatus.CROIX_GAGNE);
    }

    @Override
    public void notifierGagnantO() {
        statusJeu.set(JeuStatus.CERCLE_GAGNE);
    }

    @Override
    public void notifierEgalite() {
        statusJeu.set(JeuStatus.EGALITE);
    }

    public ReadOnlyObjectProperty<Boite.BoiteStatus> boiteStatusProperty(Position position) {
        return statusBoite.get(position).getReadOnlyProperty();
    }

    public ReadOnlyObjectProperty<JeuStatus> jeuStatusProperty() {
        return statusJeu.getReadOnlyProperty();
    }

    private static StructurePlateau<ReadOnlyObjectWrapper<Boite.BoiteStatus>> creeCasesVide() {
        StructurePlateau<ReadOnlyObjectWrapper<Boite.BoiteStatus>> data = new StructurePlateau<>();

        for (Position position : data) {
            data.set(position, new ReadOnlyObjectWrapper<>(Boite.BoiteStatus.VIDE));
        }

        return data;
    }

    private static StructurePlateau<ReadOnlyObjectProperty<Boite.BoiteStatus>> creeReadOnlyStatusBoite(@NotNull StructurePlateau<ReadOnlyObjectWrapper<Boite.BoiteStatus>> statusBoite) {
        StructurePlateau<ReadOnlyObjectProperty<Boite.BoiteStatus>> readOnlyBoite = new StructurePlateau<>();

        for (Position position : statusBoite) {
            readOnlyBoite.set(position, statusBoite.get(position).getReadOnlyProperty());
        }

        return readOnlyBoite;
    }
}