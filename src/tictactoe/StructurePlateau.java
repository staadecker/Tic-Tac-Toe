package tictactoe;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class StructurePlateau<T> implements Iterable {
    public static final int GRANDEUR = 3;

    private T[][] data = (T[][]) new Object[GRANDEUR][GRANDEUR];

    public StructurePlateau() {
    }

    public StructurePlateau(@Nullable T defaultValue) {
        Iterator<Position> iterator = iterator();

        while (iterator.hasNext()) {
            set(iterator.next(), defaultValue);
        }
    }

    public T get(Position position) {
        return data[position.rangee][position.colonne];
    }

    public void set(Position position, T valeur) {
        data[position.rangee][position.colonne] = valeur;
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
