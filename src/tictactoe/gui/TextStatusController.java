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

package tictactoe.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.jetbrains.annotations.NotNull;
import tictactoe.Jeu;

public class TextStatusController {
    private static final String MSG_TOUR_X = "Tour à X";
    private static final String MSG_TOUR_O = "Tour à O";
    private static final String MSG_O_GAGNE = "Cercle a gagné";
    private static final String MSG_X_GAGNE = "Croix a gagné";
    private static final String MSG_EGALITE = "Égalité";

    @FXML
    private Label label;

    @NotNull
    private final Jeu jeu;

    public TextStatusController(@NotNull Jeu jeu) {
        this.jeu = jeu;
    }

    @FXML
    private void initialize() {
        //Quand le status du jeu change, changer le text à la nouvelle valeur
        jeu.jeuStatusProperty().addListener((observable, oldValue, newValue) -> updateStatus(newValue));

        //Quand le tour change, changer le text (seulement si le status du jeu est INCOMPLET)
        jeu.tourProperty().addListener((observable, oldValue, newValue) -> {
            if (jeu.getJeuStatus() == Jeu.JeuStatus.INCOMPLET)
                label.setText(newValue == Jeu.Tour.CROIX ? MSG_TOUR_X : MSG_TOUR_O);
        });

        updateStatus(jeu.getJeuStatus()); //Mettre à jour le texte pour que le texte ne soit pas vide au début
    }

    /**
     * Montre le bon message en fonction du status
     */
    private void updateStatus(@NotNull Jeu.JeuStatus status) {
        switch (status) {
            case INCOMPLET:
                //Montrer à qui le tour
                label.setText(jeu.tourProperty().get() == Jeu.Tour.CROIX ? MSG_TOUR_X : MSG_TOUR_O);
                break;
            case CERCLE_GAGNE:
                label.setText(MSG_O_GAGNE);
                break;
            case CROIX_GAGNE:
                label.setText(MSG_X_GAGNE);
                break;
            case EGALITE:
                label.setText(MSG_EGALITE);
                break;
        }
    }
}
