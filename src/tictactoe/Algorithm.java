package tictactoe;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

class Algorithm {
    @Nullable
    @Contract(pure = true)
    static Joueur getGagnant(Joueur[][] status) {
        for (Joueur[] rangee : status) {
            Joueur estSuite = estSuite(rangee);

            if (estSuite != null){
                return estSuite;
            }
        }

        for (int colonne = 0; colonne < status[0].length; colonne++) {
            Joueur premiereValeur = status[0][colonne];

            if (premiereValeur == null) {
                break;
            }

            boolean win = true;

            for (int rangee = 1; rangee < status[0].length; rangee++) {
                if (premiereValeur != status[rangee][colonne]) {
                    win = false;
                    break;
                }
            }

            if (win){
                return premiereValeur;
            }
        }

        boolean win = true;

        Joueur premiereValeur = status[0][0];

        for (int index = 0; index < status.length; index++){
            if (premiereValeur != status[index][index]){
                win = false;
                break;
            }
        }

        if (win){
            return premiereValeur;
        }

        win = true;

        premiereValeur = status[status.length - 1][0];

        for (int index = 0; index < status.length; index++){
            if (premiereValeur != status[status.length - 1 - index][index]){
                win = false;
                break;
            }
        }

        if (win){
            return premiereValeur;
        }

        return null;
    }

    @Nullable
    @Contract(pure = true)
    private static Joueur estSuite(Joueur[] suite){
        Joueur premier = suite[0];

        for (Joueur joueur : suite){
            if (premier != joueur){
                return null;
            }
        }

        return premier;
    }
}
