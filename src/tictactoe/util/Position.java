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

import org.jetbrains.annotations.Contract;

/**
 * Définie une position sur le plateau de jeu.
 * (0,0) est la case en haut à gauche
 */
public class Position {
    private static final int GRANDEUR = 3;

    public final int rangee;
    public final int colonne;

    public Position(int rangee, int colonne) {
        if (rangee < 0 || colonne < 0 || rangee >= GRANDEUR || colonne >= GRANDEUR) {
            throw new IndexOutOfBoundsException("Position invalide");
        }

        this.rangee = rangee;
        this.colonne = colonne;
    }

    @Override
    public String toString() {
        return "rangee: " + rangee + " colonne: " + colonne;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Position)) return false;

        return rangee == ((Position) obj).rangee && colonne == ((Position) obj).colonne;
    }

    public boolean hasNext() {
        return !(rangee == GRANDEUR - 1 && colonne == GRANDEUR - 1);
    }

    public Position next() {
        try {
            return new Position(rangee, colonne + 1);
        } catch (IndexOutOfBoundsException e) {
            return new Position(rangee + 1, 0);
        }
    }
}