package tictactoe;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import org.jetbrains.annotations.NotNull;
import tictactoe.gui.BoiteController;
import tictactoe.util.Position;
import tictactoe.util.StructurePlateau;
import tictactoe.util.Verificateur;

/**
 * La base d'un jeu. Défini tout sauf comment et quand les joueurs vont jouer
 */
public abstract class JeuBase implements Jeu {
    @NotNull
    private final StructurePlateau<ReadOnlyObjectWrapper<BoiteController.Status>> statusBoite = creeCasesVide();

    /**
     * Les X commence toujours
     */
    @NotNull
    private final ReadOnlyObjectWrapper<StatusJeu> statusJeu = new ReadOnlyObjectWrapper<>(StatusJeu.EN_PARTIE);

    private boolean tourAX;

    JeuBase() {
        recommencer();

        statusJeu.bind(new Verificateur(creeReadOnlyStatusBoite(statusBoite)).statusProperty());  //Creer le verificateur
    }

    /**
     * Appelé pour jouer
     *
     * @param position la position de la boite où l'on veut jouer
     */
    @SuppressWarnings("ConstantConditions")
    public void jouer(Position position) {
        if (statusJeu.get() == StatusJeu.EN_PARTIE) {
            if (tourAX) {
                //Si au tour de X changer la boite pour X
                statusBoite.get(position).set(BoiteController.Status.CROIX);
            } else {
                //Si au tour de O changer la boite pour O
                statusBoite.get(position).set(BoiteController.Status.CERCLE);
            }

            tourAX = !tourAX;
        }
    }


    @Override
    public void recommencer() {
        for (Position position : statusBoite) {
            //noinspection ConstantConditions
            statusBoite.get(position).set(BoiteController.Status.VIDE);
        }

        tourAX = true;
    }

    //METHODES DE PROPRIÉTÉ JAVAFX

    @SuppressWarnings("ConstantConditions")
    public ReadOnlyObjectProperty<BoiteController.Status> boiteStatusProperty(Position position) {
        return statusBoite.get(position).getReadOnlyProperty();
    }

    public ReadOnlyObjectProperty<StatusJeu> jeuStatusProperty() {
        return statusJeu.getReadOnlyProperty();
    }

    /**
     * @return Un tableau contenant des ReadOnlyObjectWrappers avec une valeur par défaut de BoiteController.Status.VIDE
     */
    private static StructurePlateau<ReadOnlyObjectWrapper<BoiteController.Status>> creeCasesVide() {
        StructurePlateau<ReadOnlyObjectWrapper<BoiteController.Status>> data = new StructurePlateau<>();

        for (Position position : data) {
            data.set(position, new ReadOnlyObjectWrapper<>(BoiteController.Status.VIDE));
        }

        return data;
    }

    /**
     * Retourne un tableau avec les ReadOnlyObjectProperty correspondant au cases
     */
    @SuppressWarnings("ConstantConditions")
    private static StructurePlateau<ReadOnlyObjectProperty<BoiteController.Status>> creeReadOnlyStatusBoite(@NotNull StructurePlateau<ReadOnlyObjectWrapper<BoiteController.Status>> statusBoite) {
        StructurePlateau<ReadOnlyObjectProperty<BoiteController.Status>> readOnlyBoite = new StructurePlateau<>();

        for (Position position : statusBoite) {
            readOnlyBoite.set(position, statusBoite.get(position).getReadOnlyProperty());
        }

        return readOnlyBoite;
    }

    @Override
    public boolean isTourAX() {
        return tourAX;
    }
}