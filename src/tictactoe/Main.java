package tictactoe;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    @FXML
    PlateauDeJeu plateauDeJeu;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        URL location = getClass().getResource("/fxml/main.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);

        VBox root = fxmlLoader.load();

        JeuxHumainContreHumain jeux = new JeuxHumainContreHumain(plateauDeJeu);


        primaryStage.setScene(new Scene(root, 600, 600));
//        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
