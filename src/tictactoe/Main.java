package tictactoe;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        JeuxHumainContreHumain jeux = new JeuxHumainContreHumain();

        primaryStage.setScene(new Scene(jeux.getPlateauDeJeu(), 600, 600));
//        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
