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
    @FXML
    private Label label;

    @NotNull
    private final Jeu jeu;

    public TextStatusController(@NotNull Jeu jeu) {
        this.jeu = jeu;

        jeu.jeuStatusProperty().addListener((observable, oldValue, newValue) -> updateStatus(jeu.jeuStatusProperty().get()));

        jeu.tourProperty().addListener((observable, oldValue, newValue) -> {
            if (jeu.jeuStatusProperty().get() == Jeu.JeuStatus.INCOMPLET) {
                label.setText(newValue == Jeu.Tour.CROIX? "Tour à X" : "Tour à O");
            }
        });
    }

    @FXML
    private void initialize() {
        updateStatus(jeu.jeuStatusProperty().get());
    }

    private void updateStatus(@NotNull Jeu.JeuStatus valeur) {
        switch (valeur) {
            case INCOMPLET:
                label.setText(jeu.tourProperty().get() == Jeu.Tour.CROIX ? "Tour à X" : "Tour à O");
                break;
            case CERCLE_GAGNE:
                label.setText("Cercle a gagné");
                break;
            case CROIX_GAGNE:
                label.setText("Croix a gagné");
                break;
            case EGALITE:
                label.setText("Égalité");
                break;
        }
    }
}
