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
        PlateauDeJeu root = new PlateauDeJeu();
        root.setPadding(new Insets(10));

        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.show();

        for (int i = 0 ; i < 3 ; i ++ ){
            for (int y = 0 ; y < 3 ; y++){
                root.mettreX(i,y);
            }
        }
    }
}
