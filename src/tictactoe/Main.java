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

        JeuHumainContreHumain jeu = new JeuHumainContreHumain();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main.fxml"));

        MainController controller = new MainController(jeu);
        fxmlLoader.setController(controller);

        primaryStage.setScene(new Scene(fxmlLoader.load()));
//        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
