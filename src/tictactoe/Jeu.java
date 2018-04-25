/*
 * MIT License
 *
 * Copyright (c) 2018 Martin Staadecker
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package tictactoe;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import org.jetbrains.annotations.NotNull;
import tictactoe.util.CalculateurStatus;
import tictactoe.util.Position;
import tictactoe.util.Tableau;

/**
 * La base d'un jeu. Défini tout sauf comment et quand les joueurs vont jouer
 */
public class Jeu {
    /**
     * Les differents status possible pour la boite
     * Soit vide, avec un X, ou avec un O
     */
    public enum BoiteStatus {
        CROIX,
        CERCLE,
        VIDE
    }

    /**
     * Les differents status possible pour une partie
     */
    public enum JeuStatus {
        CROIX_GAGNE,
        CERCLE_GAGNE,
        EGALITE,
        INCOMPLET
    }

    /**
     * Le different tour possible
     */
    public enum Tour {
        CROIX,
        CERCLE
    }

    /**
     * Les status de chaque boite du plateau
     */
    @NotNull
    private final Tableau<ReadOnlyObjectWrapper<BoiteStatus>> statusBoite = creeCasesVide();

    /**
     * Le status du jeu qui est définit par le CalculateurStatus
     */
    private final CalculateurStatus calculateurStatus = new CalculateurStatus(creeReadOnlyStatusBoite(statusBoite));

    /**
     * Définit à qui le tour présentement
     */
    private final ReadOnlyObjectWrapper<Tour> tour = new ReadOnlyObjectWrapper<>();

    private Tour premierAJouer;

    Jeu() {
        nouvellePartie();
    }

    public void nouvellePartie() {
        for (Position position : statusBoite) {
            //noinspection ConstantConditions
            statusBoite.get(position).set(BoiteStatus.VIDE);
        }

        //Pour que le premier à jouer change chaque partie (la première partie X commence)
        if (premierAJouer == null || premierAJouer == Tour.CERCLE){
            premierAJouer = Tour.CROIX;
        } else {
            premierAJouer = Tour.CERCLE;
        }

        tour.set(premierAJouer);
    }

    /**
     * Appelé pour jouer sur une boite
     *
     * @param position la position de la boite où l'on veut jouer
     */
    void jouer(Position position) {
        if (calculateurStatus.getStatus() == JeuStatus.INCOMPLET) {
            if (getTour() == Tour.CROIX) {
                //Si au tour de X changer la boite pour X et changer le tour
                statusBoite.get(position).set(BoiteStatus.CROIX);
                tour.set(Tour.CERCLE);
            } else {
                //Si au tour de O changer la boite pour O et changer le tour
                statusBoite.get(position).set(BoiteStatus.CERCLE);
                tour.set(Tour.CROIX);
            }
        }
    }


    //METHODES DE PROPRIÉTÉ JAVAFX

    public BoiteStatus getBoiteStatus(Position position){
        return statusBoite.get(position).get();
    }

    public ReadOnlyObjectProperty<BoiteStatus> boiteStatusProperty(Position position) {
        return statusBoite.get(position).getReadOnlyProperty();
    }

    public JeuStatus getJeuStatus(){
        return calculateurStatus.getStatus();
    }

    public ReadOnlyObjectProperty<JeuStatus> jeuStatusProperty() {
        return calculateurStatus.statusProperty();
    }

    public Tour getTour() {
        return tour.get();
    }

    public ReadOnlyObjectProperty<Tour> tourProperty() {
        return tour.getReadOnlyProperty();
    }

    /**
     * @return Un tableau contenant des ReadOnlyObjectWrappers avec une valeur par défaut de BoiteStatus.VIDE
     */
    private static Tableau<ReadOnlyObjectWrapper<BoiteStatus>> creeCasesVide() {
        Tableau<ReadOnlyObjectWrapper<BoiteStatus>> data = new Tableau<>();

        for (Position position : data) {
            data.add(position, new ReadOnlyObjectWrapper<>(BoiteStatus.VIDE));
        }

        return data;
    }

    /**
     * @return le même tableau mais avec les des ReadOnlyObjectProperty au lieu de ReadOnlyObjectWrapper
     */
    private static Tableau<ReadOnlyObjectProperty<BoiteStatus>> creeReadOnlyStatusBoite(@NotNull Tableau<ReadOnlyObjectWrapper<BoiteStatus>> statusBoite) {
        Tableau<ReadOnlyObjectProperty<BoiteStatus>> readOnlyBoite = new Tableau<>();

        for (Position position : statusBoite) {
            readOnlyBoite.add(position, statusBoite.get(position).getReadOnlyProperty());
        }

        return readOnlyBoite;
    }
}