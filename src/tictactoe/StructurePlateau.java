package tictactoe;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StructurePlateau<T> implements Iterable<Position> {
    private static final int GRANDEUR = 3; //Le nombre de colonne et de rangée

    private List<List<T>> tableau = new ArrayList<>(GRANDEUR);

    public StructurePlateau() {
        this(null);
    }

    private StructurePlateau(@Nullable T valeurParDefaut) {
        for (int indexRangee = 0; indexRangee < GRANDEUR; indexRangee++) {
            //Cree une rangée avec la valeur par défaut
            ArrayList<T> rangee = new ArrayList<>(GRANDEUR);

            for (int indexColonne = 0; indexColonne < GRANDEUR; indexColonne++) {
                rangee.add(valeurParDefaut);
            }

            //Ajouter la rangée au tableau
            tableau.add(rangee);
        }
    }

    public T get(Position position) {
        return tableau.get(position.rangee).get(position.colonne);
    }

    public void set(Position position, T valeur) {
        tableau.get(position.rangee).set(position.colonne, valeur);
    }

    List<T> getDiagonaleGaucheDroit() {
        List<T> diagonale = new ArrayList<>(GRANDEUR);

        for (int i = 0; i < GRANDEUR; i++) {
            diagonale.add(tableau.get(i).get(i));
        }

        return diagonale;
    }

    List<T> getDiagonaleDroiteGauche() {
        List<T> diagonale = new ArrayList<>(GRANDEUR);

        for (int i = 0; i < GRANDEUR; i++) {
            diagonale.add(tableau.get(i).get(GRANDEUR - 1 - i));
        }

        return diagonale;
    }

    Iterator<List<T>> iteratorRangee() {
        return tableau.iterator();
    }

    Iterator<List<T>> iteratorColonne() {
        return new Iterator<List<T>>() {
            private int indexColonne;

            @Override
            public boolean hasNext() {
                return indexColonne != GRANDEUR;
            }

            @Override
            public List<T> next() {
                List<T> colonne = new ArrayList<>(GRANDEUR);

                for (int indexRangee = 0; indexRangee < GRANDEUR; indexRangee++) {
                    colonne.add(tableau.get(indexRangee).get(indexColonne));
                }

                indexColonne++;

                return colonne;
            }
        };
    }

    @NotNull
    @Override
    public Iterator<Position> iterator() {
        return new Iterator<Position>() {
            private Position position = new Position(0, 0);

            @Override
            public synchronized boolean hasNext() {
                return position.rangee != GRANDEUR;
            }

            @Override
            public synchronized Position next() {
                Position resultat = position;

                if (position.colonne + 1 < GRANDEUR) {
                    position = new Position(position.rangee, position.colonne + 1);
                } else {
                    if (position.rangee < GRANDEUR) {
                        position = new Position(position.rangee + 1, 0);
                    } else {
                        throw new IndexOutOfBoundsException("Pas de prochaine position");
                    }
                }

                return resultat;
            }
        };
    }
}
