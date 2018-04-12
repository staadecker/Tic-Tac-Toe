package tictactoe;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

@SuppressWarnings("WeakerAccess")
public class Boite extends Pane {
    private final ObjectProperty<Position> position = new ReadOnlyObjectWrapper<>();

    @FXML
    private static Image cercle;

    @FXML
    private static Image croix;

    @FXML
    private ImageView imageView;

    @FXML
    private Boite boite;

    @Nullable
    private Joueur status;

    @Nullable
    private CaseClickListener listener;

    Boite(Position position) {
        this.position.setValue(position);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/boite.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    void setListener(@Nullable CaseClickListener listener) {
        this.listener = listener;
    }

    @FXML
    protected void handleMouseClick() {
        if (status == null && listener != null) {
            listener.notifierCaseClicked(position.get());
        }
    }

    @FXML
    protected void handleMouseEnter() {
        if (status == null) boite.getStyleClass().setAll("boite-hover");
    }

    @FXML
    protected void handleMouseExit() {
        boite.getStyleClass().setAll("boite-normal");
    }

    void setStatus(Joueur status) {
        this.status = status;

        if (status == null) {
            imageView.setImage(null);
        } else if (status == Joueur.CROIX) {
            imageView.setImage(croix);
            boite.getStyleClass().setAll("boite-hover");
        } else {
            imageView.setImage(cercle);
            boite.getStyleClass().setAll("boite-normal");
        }
    }

    @Nullable
    Joueur getStatus() {
        return status;
    }
}