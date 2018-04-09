package tictactoe;

import org.jetbrains.annotations.Contract;

public class Algorithm {
    @Contract(pure = true)
    public static Joueur getGagnant(Joueur[][] status) {
        for (Joueur[] rangee : status) {
            Joueur premiereValeur = rangee[0];

            if (premiereValeur == null) {
                break;
            }

            boolean win = true;

            for (int colonne = 1; colonne < status[0].length; colonne++) {
                if (premiereValeur != rangee[colonne]) {
                    win = false;
                    break;
                }
            }

            if (win){
                return premiereValeur;
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
}
