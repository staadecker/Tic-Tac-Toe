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

import java.util.Iterator;
import java.util.List;

/**
 * Class qui vérifie pour des gagnants ou en status d'égalité puis si c'est le cas notifie le listener (dans notre cas notifie le jeu)
 * <p>
 * Au lieu de revérifier l'état du plateau après chaque tour, le listener vérifie que la rangée, colonne au diagonale qui a été modifié
 * Pour détecter des égalité, le verificateur compte le nombre de boites remplies (non-vide).
 */
public class CalculateurStatusPlateau implements ChangeListener<Jeu.JeuStatus> {
    /**
     * Le status du jeu
     */
    private final ReadOnlyObjectWrapper<Jeu.JeuStatus> status = new ReadOnlyObjectWrapper<>(Jeu.JeuStatus.INCOMPLET);

    /**
     * Nombre total de verificateur de ligne
     */
    private final int nombreDeLignes;

    /**
     * Nombre de lignes à égalité
     */
    private int nombreDeEgalite = 0;

    /**
     * Crée un vérificateur de ligne pour chaque colonne, rangée, diagonale et s'ajoute comme listener au vérificateur de ligne
     */
    public CalculateurStatusPlateau(@NotNull Tableau<ReadOnlyObjectProperty<Jeu.BoiteStatus>> statusBoites) {
        int compterDeLignes = 0;

        //Pour chaque rangée créé un vérificateur de ligne
        Iterator<List<ReadOnlyObjectProperty<Jeu.BoiteStatus>>> iteratorRangee = statusBoites.iteratorRangee();

        while (iteratorRangee.hasNext()) {
            new CalculateurStatusLigne(iteratorRangee.next())
                    .statusProperty().addListener(this);
            compterDeLignes++;
        }

        //Créé un vérificateur de ligne pour chaque colonne
        Iterator<List<ReadOnlyObjectProperty<Jeu.BoiteStatus>>> iteratorColonne = statusBoites.iteratorColonne();

        while (iteratorColonne.hasNext()) {
            new CalculateurStatusLigne(iteratorColonne.next())
                    .statusProperty().addListener(this);
            compterDeLignes++;
        }

        //Crée un vérificateur de ligne pour les deux diagonales
        new CalculateurStatusLigne(statusBoites.getDiagonaleGaucheDroit())
                .statusProperty().addListener(this);
        new CalculateurStatusLigne(statusBoites.getDiagonaleDroiteGauche())
                .statusProperty().addListener(this);
        compterDeLignes += 2;

        this.nombreDeLignes = compterDeLignes;
    }

    /**
     * Appelé quand un des vérificateurs de lignes change de status
     */
    @Override
    public void changed(ObservableValue<? extends Jeu.JeuStatus> observable, Jeu.JeuStatus oldValue, Jeu.JeuStatus newValue) {
        //Mettre à jour le compter d'égalité
        if (oldValue == Jeu.JeuStatus.EGALITE) nombreDeEgalite--;
        if (newValue == Jeu.JeuStatus.EGALITE) nombreDeEgalite++;

        //Si toutes les lignes sont égalités alors le plateau est égalité
        if (nombreDeEgalite == nombreDeLignes) {
            status.set(Jeu.JeuStatus.EGALITE);
            return;
        }

        //Sinon le plateau à la valeur de la ligne (tant que ce n'est pas égalité
        if (newValue != Jeu.JeuStatus.EGALITE) status.set(newValue);
    }

    public ReadOnlyObjectProperty<Jeu.JeuStatus> statusProperty() {
        return status.getReadOnlyProperty();
    }

    public Jeu.JeuStatus getStatus() {
        return status.get();
    }
}
