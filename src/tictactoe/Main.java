package tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tictactoe.gui.PointageController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
//        JeuxHumainContreHumain jeux = new JeuxHumainContreHumain();

        URL location = getClass().getResource("/main.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);

        primaryStage.setScene(new Scene(fxmlLoader.load(), 600, 600));
//        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
