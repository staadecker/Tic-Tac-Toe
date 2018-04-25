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
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;
import tictactoe.ClickListener;
import tictactoe.Jeu;
import tictactoe.util.Position;
import tictactoe.util.Tableau;

import java.io.IOException;

/**
 * Class qui controlle l'interface graphique du jeu
 */
public class MainController {
    /**
     * Le modèle de jeu
     */
    @NotNull
    private final Jeu jeu;

    private final ClickListener listener;

    @FXML
    private GridPane plateauDeJeu;

    /**
     * @param jeu le model de jeu
     */
    public MainController(@NotNull Jeu jeu, ClickListener listener) {
        this.jeu = jeu;
        this.listener = listener;
    }

    @FXML
    private void initialize() {
        Position position = new Position(0,0);

        //Ajouter chaque boite au grid pane
        while (position.hasNext()) {
            //Attacher l'objet au ficihier fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/boite.fxml"));
            fxmlLoader.setController(new BoiteController(jeu, listener, position));

            try {
                plateauDeJeu.add(fxmlLoader.load(), position.colonne, position.rangee);
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }

            position = position.next();
        }
    }

    @FXML
    private void recommencer() {
        jeu.nouvellePartie();
    }
}
