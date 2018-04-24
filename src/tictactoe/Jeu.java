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

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
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
public class Jeu {
    @NotNull
    private final StructurePlateau<ReadOnlyObjectWrapper<BoiteController.Status>> statusBoite = creeCasesVide();

    /**
     * Les X commence toujours
     */
    @NotNull
    private final ReadOnlyObjectWrapper<StatusJeu> statusJeu = new ReadOnlyObjectWrapper<>(StatusJeu.EN_PARTIE);

    private final ReadOnlyBooleanWrapper tourAX = new ReadOnlyBooleanWrapper();

    Jeu() {
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
            if (tourAX.get()) {
                //Si au tour de X changer la boite pour X
                statusBoite.get(position).set(BoiteController.Status.CROIX);
            } else {
                //Si au tour de O changer la boite pour O
                statusBoite.get(position).set(BoiteController.Status.CERCLE);
            }

            tourAX.set(!tourAX.get());
        }
    }


    public void recommencer() {
        for (Position position : statusBoite) {
            //noinspection ConstantConditions
            statusBoite.get(position).set(BoiteController.Status.VIDE);
        }

        tourAX.set(true);
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

    public ReadOnlyBooleanProperty tourAXProperty() {
        return tourAX.getReadOnlyProperty();
    }
}