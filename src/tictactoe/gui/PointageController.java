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

import javafx.animation.Transition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import tictactoe.Jeu;

/**
 * Controlle la zone qui montre les points
 */
public class PointageController {
    private static final String UNITE_POINT = "pt";

    @FXML
    private Text textScoreCercle;
    @FXML
    private Text textScoreCroix;

    @FXML
    private VBox boxCercle;

    @FXML
    private VBox boxCroix;

    private final SimpleIntegerProperty scoreCercle = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty scoreCroix = new SimpleIntegerProperty(0);

    public PointageController(Jeu jeu) {
        //Si la propriété de status de jeu change...
        jeu.jeuStatusProperty().addListener(
                (observable, oldValue, newValue) -> {
                    switch (newValue) {
                        //Si les X gagne, +1 pour les X
                        case X_GAGNE:
                            scoreCroix.set(scoreCroix.getValue() + 1);
                            break;
                        //Si les O gagne +1 pour les O
                        case O_GAGNE:
                            scoreCercle.set(scoreCercle.getValue() + 1);
                            break;
                        case EGALITE:
                            scoreCroix.set(scoreCroix.getValue() + 1);
                            scoreCercle.set(scoreCercle.getValue() + 1);
                            break;
                    }
                }
        );
    }

    @FXML
    public void initialize() {
        //Attacher la boite de texte avec le score propriété de score
        //Bindings.concat() pour mettre tout ajouter les unités
        textScoreCercle.textProperty().bind(Bindings.concat(scoreCercle, " ", UNITE_POINT));
        textScoreCroix.textProperty().bind(Bindings.concat(scoreCroix, " ", UNITE_POINT));

        final Transition flashCercle = new FlashTransition(boxCercle);
        final Transition flashCroix = new FlashTransition(boxCroix);

        textScoreCercle.textProperty().addListener((observable, oldValue, newValue) -> flashCercle.play());
        textScoreCroix.textProperty().addListener((observable, oldValue, newValue) -> flashCroix.play());
    }
}
