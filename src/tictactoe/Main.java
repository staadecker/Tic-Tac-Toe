package tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        JeuxHumainContreHumain jeux = new JeuxHumainContreHumain();

        Parent pointage = FXMLLoader.load(getClass().getResource("/pointage.fxml"));

        VBox root = new VBox(pointage, jeux.getPlateauDeJeu());


        primaryStage.setScene(new Scene(root, 600, 600));
//        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
