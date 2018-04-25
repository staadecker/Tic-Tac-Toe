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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Une structure de données pour le plateau de jeu tic tac toe
 * Permet aux autres class de garder un objet dans une position du tableau.
 * Par example le {@link tictactoe.gui.MainController} garde toutes les boites du plateau de jeu dans cette structure de données
 * <p>
 * Les classes peuvent accéder les données dans la structure à l'aide de {@link Position} sans devoir se soucier de comment les données sont organisées
 * <p>
 * À l'interne, la structure de données une liste qui stocke toutes les données en ordre de gauche à droite puis de haut en bas
 *
 * @param <T> Le type de donnée stockée
 */
public class Tableau<T> implements Iterable<Position> {
    private static final int GRANDEUR = 3; //Le nombre de colonne et de rangée

    //TODO Change to use single list
    @NotNull
    private final List<T> tableau = new ArrayList<>(GRANDEUR * GRANDEUR);

    /**
     * @param position la position de la donnée désirée
     * @return la donnée désirée
     */
    @NotNull
    public T get(@NotNull Position position) {
        return tableau.get(getIndex(position));
    }

    public void add(@NotNull Position position, @NotNull T valeur){
        tableau.add(getIndex(position), valeur);
    }

    /**
     * @return une liste avec les données dans la diagonale qui va d'en haut à gauche jusqu'en bas à droite
     */
    @NotNull
    List<T> getDiagonaleGaucheDroit() {
        List<T> diagonale = new ArrayList<>(GRANDEUR);

        for (int i = 0; i < GRANDEUR; i++) {
            diagonale.add(tableau.get(getIndex(i, i)));
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
            diagonale.add(tableau.get(getIndex(i, GRANDEUR - 1 - i)));
        }

        return diagonale;
    }

    private int getIndex(Position position){
        return getIndex(position.rangee, position.colonne);
    }

    private int getIndex(int rangee, int colonne) {
        return GRANDEUR * rangee + colonne;
    }

    /**
     * @return un iterator qui "iterate" sur chaque rangée
     */
    @NotNull
    Iterator<List<T>> iteratorRangee() {
        return new Iterator<List<T>>() {
            private int rangee;

            @Override
            public boolean hasNext() {
                return rangee != GRANDEUR;
            }

            @Override
            public List<T> next() {
                List<T> rangeeResultat = new ArrayList<>(GRANDEUR);

                //Ajouter pour chaque rangée l'objet de la colonne à la liste
                for (int colonne = 0; colonne < GRANDEUR; colonne++) {
                    rangeeResultat.add(tableau.get(getIndex(rangee, colonne)));
                }

                rangee++; //Passer à la prochaine colonne

                return rangeeResultat;
            }
        };
    }

    /**
     * @return un iterator qui "iterate" sur chaque colonne
     */
    @NotNull
    Iterator<List<T>> iteratorColonne() {
        return new Iterator<List<T>>() {
            private int colonne;

            @Override
            public boolean hasNext() {
                return colonne != GRANDEUR;
            }

            @NotNull
            @Override
            public List<T> next() {
                List<T> colonneResultat = new ArrayList<>(GRANDEUR);

                //Ajouter pour chaque rangée l'objet de la colonne à la liste
                for (int rangee = 0; rangee < GRANDEUR; rangee++) {
                    colonneResultat.add(tableau.get(getIndex(rangee, colonne)));
                }

                colonne++; //Passer à la prochaine colonne

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
