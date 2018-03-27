package tictactoe;

import tictactoe.gui.Case;
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
        PlateauDeJeu root = new PlateauDeJeu(null);
        root.setPadding(new Insets(10));

        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.show();

        for (int id = 0 ; id < 9 ; id ++ ){
            root.setStatus(id, Case.Status.X);
        }
    }
}
