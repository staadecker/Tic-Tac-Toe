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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Une structure de données pour le plateau de jeu tic tac toe
 * Permet au class de garder un objet dans une position du tableau.
 * Par example le {@link tictactoe.gui.MainController} garde toutes les boites du plateau de jeu dans cette structure de données
 * <p>
 * Les classes peuvent accéder les données dans la structure à l'aide de {@link Position} sans devoir se soucier de comment les données sont organisées
 * <p>
 * À l'interne, la structure de données utilisé deux liste une dans l'autre pour stocker les données.
 *
 * @param <T> Le type de donnée stocké
 */
public class StructurePlateau<T> implements Iterable<Position> {
    private static final int GRANDEUR = 3; //Le nombre de colonne et de rangée

    @NotNull
    private final List<List<T>> tableau = new ArrayList<>(GRANDEUR);

    public StructurePlateau() {
        for (int indexRangee = 0; indexRangee < GRANDEUR; indexRangee++) {
            //Cree une rangée avec la valeur par défaut
            ArrayList<T> rangee = new ArrayList<>(GRANDEUR);

            for (int indexColonne = 0; indexColonne < GRANDEUR; indexColonne++) {
                rangee.add(null);
            }

            tableau.add(rangee); //Ajouter la rangée au tableau
        }
    }

    /**
     * @param position la position de la donnée désirée
     * @return la donnée désirée
     */
    @Nullable
    public T get(@NotNull Position position) {
        return tableau.get(position.rangee).get(position.colonne);
    }

    /**
     * Change ou définit l'objet
     *
     * @param position la position à changer
     * @param valeur   la nouvelle valeur
     */
    public void set(@NotNull Position position, @Nullable T valeur) {
        tableau.get(position.rangee).set(position.colonne, valeur);
    }

    /**
     * @return le nombre de données
     */
    int size() {
        return GRANDEUR * GRANDEUR;
    }

    /**
     * @return une liste avec les données dans la diagonale qui va d'en haut à gauche jusqu'en bas à droite
     */
    @NotNull
    List<T> getDiagonaleGaucheDroit() {
        List<T> diagonale = new ArrayList<>(GRANDEUR);

        for (int i = 0; i < GRANDEUR; i++) {
            diagonale.add(tableau.get(i).get(i));
        }

        return diagonale;
    }

    /**
     * @return une liste avec les données dans la diagonale qui va d'en haut à droite jusqu'en bas à gauche
     */
    @NotNull
    List<T> getDiagonaleDroiteGauche() {
        List<T> diagonale = new ArrayList<>(GRANDEUR);

        for (int i = 0; i < GRANDEUR; i++) {
            diagonale.add(tableau.get(i).get(GRANDEUR - 1 - i));
        }

        return diagonale;
    }

    /**
     * @return un iterator qui "iterate" sur chaque rangée
     */
    @NotNull
    Iterator<List<T>> iteratorRangee() {
        return tableau.iterator();
    }

    /**
     * @return un iterator qui "iterate" sur chaque colonne
     */
    @NotNull
    Iterator<List<T>> iteratorColonne() {
        return new Iterator<List<T>>() {
            private int indexColonne;

            @Override
            public boolean hasNext() {
                return indexColonne != GRANDEUR;
            }

            @NotNull
            @Override
            public List<T> next() {
                List<T> colonneResultat = new ArrayList<>(GRANDEUR);

                //Ajouter pour chaque rangée l'objet de la colonne à la liste
                for (int indexRangee = 0; indexRangee < GRANDEUR; indexRangee++) {
                    colonneResultat.add(tableau.get(indexRangee).get(indexColonne));
                }

                indexColonne++; //Passer à la prochaine colonne

                return colonneResultat;
            }
        };
    }

    /**
     * @return un iterator qui passe sur toutes les position de la structure de données
     */
    @NotNull
    @Override
    public Iterator<Position> iterator() {
        return new Iterator<Position>() {
            private Position position = new Position(0, 0); //Position de départ

            @Override
            public synchronized boolean hasNext() {
                return position.rangee != GRANDEUR;
            }

            @Override
            public synchronized Position next() {
                Position resultat = position;

                //Si la position est trop grande lancer une error
                if (resultat.rangee >= GRANDEUR || resultat.colonne >= GRANDEUR) {
                    throw new IndexOutOfBoundsException("Pas de prochaine position");
                }

                //Passer à la prochaine position
                if (position.colonne + 1 == GRANDEUR) { //Si on est sur la dernière colonne
                    position = new Position(position.rangee + 1, 0);
                } else {
                    position = new Position(position.rangee, position.colonne + 1);
                }

                return resultat;
            }
        };
    }
}
