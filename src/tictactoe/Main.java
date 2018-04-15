package tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.gui.MainController;

import java.io.IOException;

public class Main extends Application {
    private static final String TITRE = "Jeu de Tic-Tac-Toe";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle(TITRE);

        Jeu jeux = new Jeu();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));

        fxmlLoader.setController(new MainController(jeux));

        primaryStage.setScene(new Scene(fxmlLoader.load()));
//        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
