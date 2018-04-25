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

package tictactoe.util;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.jetbrains.annotations.NotNull;
import tictactoe.Jeu;

import java.util.List;

/**
 * Object qui vérifie une ligne (rangée, colonne ou diagonale) pour des gagnants (par example des colonnes ou des rangée)
 */
class CalculateurStatusLigne implements ChangeListener<Jeu.BoiteStatus> {

    /**
     * Le status de la ligne
     */
    private final ReadOnlyObjectWrapper<Jeu.JeuStatus> status = new ReadOnlyObjectWrapper<>(Jeu.JeuStatus.INCOMPLET);

    /**
     * La liste de boites dans la ligne
     */
    @NotNull
    private final List<ReadOnlyObjectProperty<Jeu.BoiteStatus>> ligne;

    CalculateurStatusLigne(List<ReadOnlyObjectProperty<Jeu.BoiteStatus>> ligne) {
        this.ligne = ligne;

        //Se faire notifier si une des boites dans la ligne change
        for (ReadOnlyObjectProperty<Jeu.BoiteStatus> boite : ligne) {
            boite.addListener(this);
        }
    }

    /**
     * Appelé quand une des boites dans la ligne change
     */
    @Override
    public void changed(ObservableValue<? extends Jeu.BoiteStatus> observable, Jeu.BoiteStatus oldValue, Jeu.BoiteStatus newValue) {
        //Si il y a une boite de vide c'est INCOMPLET
        for (ReadOnlyObjectProperty<Jeu.BoiteStatus> boite : ligne) {
            if (boite.get() == Jeu.BoiteStatus.VIDE) {
                status.set(Jeu.JeuStatus.INCOMPLET);
                return;
            }
        }

        //Si aucune boite VIDE mais les boites ne sont pas toutes identiques c'est EGALITE
        for (ReadOnlyObjectProperty<Jeu.BoiteStatus> boite : ligne) {
            if (newValue != boite.get()) {
                status.set(Jeu.JeuStatus.EGALITE);
                return;
            }
        }

        //Sinon les boites ont tous la valeur et il y a un gagnant
        status.set(newValue == Jeu.BoiteStatus.CROIX ? Jeu.JeuStatus.CROIX_GAGNE : Jeu.JeuStatus.CERCLE_GAGNE);
    }

    ReadOnlyObjectProperty<Jeu.JeuStatus> statusProperty() {
        return status.getReadOnlyProperty();
    }
}
