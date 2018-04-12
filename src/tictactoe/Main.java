package tictactoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
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

        VBox root = new VBox(jeux.getPlateauDeJeu());
        VBox.setVgrow(jeux.getPlateauDeJeu(), Priority.ALWAYS);
        root.setPadding(new Insets(10));
        root.setSpacing(10);

        primaryStage.setScene(new Scene(root, 600, 600));
//        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
