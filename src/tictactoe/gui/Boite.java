package tictactoe.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import tictactoe.util.Position;

import java.io.IOException;

public class Boite extends Pane {
    private final Position position;

    Boite(Position position, Callback<Class<?>, Object> controllerFactory) {
        super();

        this.position = position;

        //Attacher l'objet au ficihier fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/boite.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setControllerFactory(controllerFactory);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Position getPosition() {
        return position;
    }
}
