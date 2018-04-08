package tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        JeuxHumainContreHumain jeux = new JeuxHumainContreHumain();

        primaryStage.setScene(new Scene(jeux.getPlateauDeJeu()));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
