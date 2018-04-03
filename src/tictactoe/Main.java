package tictactoe;

import javafx.scene.layout.StackPane;
import tictactoe.gui.Boite;
import tictactoe.gui.PlateauDeJeu;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Jeux jeux = new Jeux(new JoueurHumain(), new JoueurHumain());

        primaryStage.setScene(new Scene(jeux.getPlateauDeJeu()));
        primaryStage.setMaximized(true);
        primaryStage.show();

        for (int id = 0 ; id < 9 ; id ++ ){
            jeux.getPlateauDeJeu().setStatus(id, Boite.Status.X);
        }
    }
}
