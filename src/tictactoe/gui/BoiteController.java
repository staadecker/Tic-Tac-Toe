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

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tictactoe.ClickListener;
import tictactoe.Jeu;
import tictactoe.StatusJeu;
import tictactoe.util.Position;

/**
 * Une boite (case) du plateau de jeu tic-tac-toe pour l'interface graphique
 */
public class BoiteController {
    private static final Image IMAGE_X = new Image("/image/x.png");
    private static final Image IMAGE_O = new Image("/image/o.png");

    /**
     * Les differents status possible pour la boite
     * Soit vide, avec un X, ou avec un O
     */
    public enum Status {
        CROIX,
        CERCLE,
        VIDE
    }

    @FXML
    private Pane boite;

    @FXML
    private ImageView image;

    private final Position position;

    /**
     * Status de la boite
     * La valeur par défaut est vide
     */
    @NotNull
    private final SimpleObjectProperty<Status> status = new SimpleObjectProperty<>(Status.VIDE);

    /**
     * Listener à notifier quand la boite est appuyée
     */
    @Nullable
    private ClickListener listener;

    private final Jeu jeu;

    BoiteController(Jeu jeu, @Nullable ClickListener listener, Position position) {
        this.listener = listener;
        this.jeu = jeu;
        this.position = position;
    }

    @FXML
    private void initialize() {
        status.bind(jeu.boiteStatusProperty(position));

        //Quand le status change changer la boite
        status.addListener(
                (observable, oldValue, newValue) -> {
                    switch (newValue) {
                        case VIDE:
                            image.setImage(null); //Enelever l'image
                            break;
                        case CROIX:
                            // Mettre l'image X et enlever la bordure-hover
                            image.setOpacity(1);
                            image.setImage(IMAGE_X);
                            boite.getStyleClass().setAll("bordure-normale");
                            break;
                        case CERCLE:
                            // Mettre l'image O et enlever la bordure-hover
                            image.setOpacity(1);
                            image.setImage(IMAGE_O);
                            boite.getStyleClass().setAll("bordure-normale");
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
        if (status.get().equals(Status.VIDE) && listener != null) {
            listener.notifierBoiteClicked(position);
        }
    }

    /**
     * Appelé quand le curseur rentre dans la boite
     * Si la boite est vide la surligner
     */
    @FXML
    private void handleMouseEnter() {
        if (status.get().equals(Status.VIDE) && jeu.jeuStatusProperty().get() == StatusJeu.EN_PARTIE) {
            boite.getStyleClass().setAll("bordure-hover");

            image.setOpacity(0.5);
            if (jeu.tourAXProperty().get()) {
                image.setImage(IMAGE_X);
            } else {
                image.setImage(IMAGE_O);
            }
        }
    }

    /**
     * Appelé quand le curseur quitte la boite
     */
    @FXML
    private void handleMouseExit() {
        boite.getStyleClass().setAll("bordure-normale");
        if (status.get() == Status.VIDE) image.setImage(null);
    }
}