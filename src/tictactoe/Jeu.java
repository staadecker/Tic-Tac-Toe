package tictactoe;

import javafx.beans.property.ReadOnlyObjectProperty;
import tictactoe.gui.BoiteController;
import tictactoe.util.Position;

/**
 * Défini le model de jeu
 * Extends BoiteController.ClickListener car un jeu voudra toujours savoir quand une des boites a été appuyé
 */
public interface Jeu extends ClickListener {
    /**
     * Pour jouer dans une boite
     *
     * @param position la position de la boite où l'on veut jouer
     */
    void jouer(Position position);

    void recommencer();

    /**
     * @return le status du jeu actuel
     */
    ReadOnlyObjectProperty<StatusJeu> jeuStatusProperty();

    boolean isTourAX();

    /**
     * Le status d'une des boites
     *
     * @param position la position de la boite
     * @return le status
     */
    ReadOnlyObjectProperty<BoiteController.Status> boiteStatusProperty(Position position);
}
