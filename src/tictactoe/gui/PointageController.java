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

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import tictactoe.Jeu;

/**
 * Controlle la zone qui montre les points
 */
//TODO Make class for each score keeping box
public class PointageController {
    private static final String UNITE = "pt";

    @FXML
    private Text textScoreCercle;

    @FXML
    private Text textScoreCroix;

    @FXML
    private Text textScoreEgalite;

    @FXML
    private VBox boxEgalite;

    @FXML
    private VBox boxCercle;

    @FXML
    private VBox boxCroix;

    //Propriétés qui représentent le score des deux joueurs
    private final SimpleIntegerProperty scoreCercle = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty scoreCroix = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty scoreEgalite = new SimpleIntegerProperty(0);

    public PointageController(Jeu jeu) {
        //Quand le status du jeu change, changer la propriété du score
        jeu.jeuStatusProperty().addListener(
                (observable, oldValue, newValue) -> {
                    switch (newValue) {
                        //Si les X gagne, +1 pour les X
                        case CROIX_GAGNE:
                            scoreCroix.set(scoreCroix.get() + 1);
                            break;
                        //Si les O gagne +1 pour les O
                        case CERCLE_GAGNE:
                            scoreCercle.set(scoreCercle.get() + 1);
                            break;
                        case EGALITE:
                            scoreEgalite.set(scoreEgalite.get() + 1);
                            break;
                    }
                }
        );
    }

    @FXML
    public void initialize() {
        //Attacher la boite de texte avec les proprétées de score
        //Bindings.concat() pour ajouter les unités
        textScoreCercle.textProperty().bind(Bindings.concat(scoreCercle, " ", UNITE));
        textScoreCroix.textProperty().bind(Bindings.concat(scoreCroix, " ", UNITE));
        textScoreEgalite.textProperty().bind(Bindings.concat(scoreEgalite, " ", UNITE));

        //Quand le text change, appliquer la transition
        textScoreCercle.textProperty().addListener((observable, oldValue, newValue) -> new FlashTransition(boxCercle).play());
        textScoreCroix.textProperty().addListener((observable, oldValue, newValue) -> new FlashTransition(boxCroix).play());
        textScoreEgalite.textProperty().addListener((observable, oldValue, newValue) -> new FlashTransition(boxEgalite).play());
    }
}
