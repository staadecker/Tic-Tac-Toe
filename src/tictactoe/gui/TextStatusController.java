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
import tictactoe.StatusJeu;

public class TextStatusController {
    @FXML
    private Label label;

    private final Jeu jeu;

    public TextStatusController(Jeu jeu) {
        this.jeu = jeu;

        jeu.jeuStatusProperty().addListener((observable, oldValue, newValue) -> updateStatus(jeu.jeuStatusProperty().get()));

        jeu.tourAXProperty().addListener((observable, oldValue, newValue) -> {
            if (jeu.jeuStatusProperty().get() == StatusJeu.EN_PARTIE) {
                label.setText(newValue ? "Tour à X" : "Tour à O");
            }
        });
    }

    @FXML
    private void initialize() {
        updateStatus(jeu.jeuStatusProperty().get());
    }

    private void updateStatus(@NotNull StatusJeu valeur) {
        switch (valeur) {
            case EN_PARTIE:
                label.setText(jeu.tourAXProperty().get() ? "Tour à X" : "Tour à O");
                break;
            case O_GAGNE:
                label.setText("Cercle a gagné");
                break;
            case X_GAGNE:
                label.setText("Croix a gagné");
                break;
            case EGALITE:
                label.setText("Égalité");
                break;
        }
    }
}
