package tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        JeuxHumainContreHumain jeux = new JeuxHumainContreHumain();

        Parent pointage = FXMLLoader.load(getClass().getResource("/pointage.fxml"));

        VBox root = new VBox(pointage, jeux.getPlateauDeJeu());
        VBox.setVgrow(jeux.getPlateauDeJeu(), Priority.ALWAYS);
        root.setPadding(new Insets(10));
        root.setSpacing(10);

        primaryStage.setScene(new Scene(root, 600, 600));
//        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
