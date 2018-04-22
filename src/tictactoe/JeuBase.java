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
    @NotNull
    private final StructurePlateau<ReadOnlyObjectWrapper<Boite.Status>> statusBoite = creeCasesVide();

    /**
     * Les X commence toujours
     */
    @NotNull
    private final ReadOnlyObjectWrapper<JeuStatus> statusJeu = new ReadOnlyObjectWrapper<>(JeuStatus.TOUR_CROIX);

    JeuBase() {
        new Verificateur(this, creeReadOnlyStatusBoite(statusBoite)); //Créer le vérificateur
    }

    /**
     * Appelé pour jouer
     *
     * @param position la position de la boite où l'on veut jouer
     */
    @SuppressWarnings("ConstantConditions")
    public void jouer(Position position) {
        switch (statusJeu.get()) {
            case TOUR_CROIX:
                //Si au tour de X changer la boite pour X
                statusBoite.get(position).set(Boite.Status.CROIX);
                break;
            case TOUR_CERCLE:
                //Si au tour de O changer la boite pour O
                statusBoite.get(position).set(Boite.Status.CERCLE);
                break;
            default:
                return;
        }

        switch (statusJeu.get()) {
            case EGALITE:
            case CROIX_GAGNE:
            case CERCLE_GAGNE:
                //Si le changement de la boite à créé un gagnant et le vérificateur à notifier le jeu du gagnant
                System.out.println(statusJeu.get().toString());
                break;
            //Sinon changer le tour à l'autre joueur
            case TOUR_CERCLE:
                statusJeu.set(JeuStatus.TOUR_CROIX);
                break;
            case TOUR_CROIX:
                statusJeu.set(JeuStatus.TOUR_CERCLE);
                break;
        }
    }

    //METHODS APPELÉ PAR LE VÉRIFICATEUR

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

    //METHODES DE PROPRIÉTÉ JAVAFX

    @SuppressWarnings("ConstantConditions")
    public ReadOnlyObjectProperty<Boite.Status> boiteStatusProperty(Position position) {
        return statusBoite.get(position).getReadOnlyProperty();
    }

    public ReadOnlyObjectProperty<JeuStatus> jeuStatusProperty() {
        return statusJeu.getReadOnlyProperty();
    }

    @Override
    public JeuStatus getJeuStatus() {
        return statusJeu.get();
    }

    /**
     * @return Un tableau contenant des ReadOnlyObjectWrappers avec une valeur par défaut de Boite.Status.VIDE
     */
    private static StructurePlateau<ReadOnlyObjectWrapper<Boite.Status>> creeCasesVide() {
        StructurePlateau<ReadOnlyObjectWrapper<Boite.Status>> data = new StructurePlateau<>();

        for (Position position : data) {
            data.set(position, new ReadOnlyObjectWrapper<>(Boite.Status.VIDE));
        }

        return data;
    }

    /**
     * Retourne un tableau avec les ReadOnlyObjectProperty correspondant au cases
     */
    @SuppressWarnings("ConstantConditions")
    private static StructurePlateau<ReadOnlyObjectProperty<Boite.Status>> creeReadOnlyStatusBoite(@NotNull StructurePlateau<ReadOnlyObjectWrapper<Boite.Status>> statusBoite) {
        StructurePlateau<ReadOnlyObjectProperty<Boite.Status>> readOnlyBoite = new StructurePlateau<>();

        for (Position position : statusBoite) {
            readOnlyBoite.set(position, statusBoite.get(position).getReadOnlyProperty());
        }

        return readOnlyBoite;
    }
}