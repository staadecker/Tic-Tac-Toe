package tictactoe;

import javafx.beans.property.ReadOnlyObjectProperty;
import tictactoe.gui.Boite;
import tictactoe.util.Position;

/**
 * Défini le model de jeu
 * Extends Boite.ClickListener car un jeu voudra toujours savoir quand une des boites a été appuyé
 */
public interface Jeu extends ClickListener {
    /**
     * Les différents status possible pour un jeu
     */
    enum JeuStatus {
        TOUR_CROIX,
        TOUR_CERCLE,
        EGALITE,
        CROIX_GAGNE,
        CERCLE_GAGNE
    }

    /**
     * Pour jouer dans une boite
     *
     * @param position la position de la boite où l'on veut jouer
     */
    void jouer(Position position);

    /**
     * @return le status du jeu actuel
     */
    ReadOnlyObjectProperty<JeuBase.JeuStatus> jeuStatusProperty();

    /**
     * Le status d'une des boites
     *
     * @param position la position de la boite
     * @return le status
     */
    ReadOnlyObjectProperty<Boite.Status> boiteStatusProperty(Position position);
}
