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

package tictactoe;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.gui.MainController;
import tictactoe.gui.PointageController;
import tictactoe.gui.TextStatusController;

import java.io.IOException;

public class Main extends Application {
    private static final String TITRE = "Jeu de Tic-Tac-Toe";

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle(TITRE);

        Jeu jeu = new Jeu(); //Créer le jeu

        HumainContreHumain strategie = new HumainContreHumain(jeu); //Créer la stratégie

        //Créer l'interface
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main.fxml"));

        //Définir comment les controlleurs devraient être créés
        fxmlLoader.setControllerFactory(param -> {
            if (param == MainController.class) return new MainController(jeu, strategie);
            if (param == PointageController.class) return new PointageController(jeu);
            if (param == TextStatusController.class) return new TextStatusController(jeu);
            throw new RuntimeException("Controller inconnu");
        });

        primaryStage.setScene(new Scene(fxmlLoader.load()));
        primaryStage.show();
    }
}
