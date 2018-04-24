package tictactoe.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import tictactoe.util.Position;

import java.io.IOException;

public class Boite extends Pane {
    private final Position position;

    Boite(Position position) {
        super();

        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
