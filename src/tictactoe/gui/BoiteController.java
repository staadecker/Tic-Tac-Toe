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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tictactoe.ClickListener;
import tictactoe.Jeu;
import tictactoe.util.Position;

/**
 * Une boite (case) du plateau de jeu tic-tac-toe pour l'interface graphique
 */
class BoiteController {

    //Du fichier FXML

    @FXML
    private Image imageO;

    @FXML
    private Image imageX;

    @FXML
    private Pane contenaire;

    @FXML
    private ImageView imageView;

    /**
     * La position de la boite
     */
    @NotNull
    private final Position position;

    /**
     * Le listener à notifier quand la boite est appuyée
     */
    @Nullable
    private final ClickListener listener;

    @NotNull
    private final Jeu jeu;

    BoiteController(@NotNull Jeu jeu, @Nullable ClickListener listener, @NotNull Position position) {
        this.listener = listener;
        this.jeu = jeu;
        this.position = position;
    }

    /**
     * Appelé par JavaFX
     */
    @FXML
    private void initialize() {
        //Quand le status de la boite change, changer la boite
        jeu.boiteStatusProperty(position).addListener(
                (observable, oldValue, newValue) -> {
                    switch (newValue) {
                        case VIDE:
                            imageView.setImage(null); //Enelever l'image
                            break;
                        case CROIX:
                            // Mettre l'image X et enlever la bordure-hover
                            setX(false);
                            contenaire.getStyleClass().setAll("bordure-normale");
                            break;
                        case CERCLE:
                            // Mettre l'image O et enlever la bordure-hover
                            setO(false);
                            contenaire.getStyleClass().setAll("bordure-normale");
                    }
                }
        );
    }

    /**
     * Appelé quand la boite a été clické
     * Si un listener est designé et la boite est vide notifier le listener que la boite a été clické
     */
    @FXML
    private void handleMouseClick() {
        if (jeu.boiteStatusProperty(position).get().equals(Jeu.BoiteStatus.VIDE) && listener != null) {
            listener.notifierBoiteClicked(position);
        }
    }

    /**
     * Appelé quand le curseur rentre dans la boite
     * Si la boite est vide la surligner
     */
    @FXML
    private void handleMouseEnter() {
        if (jeu.boiteStatusProperty(position).get().equals(Jeu.BoiteStatus.VIDE) && jeu.jeuStatusProperty().get() == Jeu.JeuStatus.INCOMPLET) {
            contenaire.getStyleClass().setAll("bordure-hover");

            if (jeu.tourProperty().get() == Jeu.Tour.CROIX) {
                setX(true);
            } else {
                setO(true);
            }
        }
    }

    /**
     * Appelé quand le curseur quitte la boite
     */
    @FXML
    private void handleMouseExit() {
        contenaire.getStyleClass().setAll("bordure-normale");
        if (jeu.boiteStatusProperty(position).get() == Jeu.BoiteStatus.VIDE) imageView.setImage(null);
    }

    private void setX(boolean grayedOut){
        setImage(grayedOut, imageX);
    }

    private void setO(boolean grayedOut){
        setImage(grayedOut, imageO);
    }

    private void setImage(boolean grayedOut, Image image) {
        imageView.setOpacity(grayedOut ? 0.5 : 1);
        imageView.setImage(image);
    }
}