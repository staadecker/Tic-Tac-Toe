package tictactoe;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

class Boite extends Pane {
    private final ObjectProperty<Position> position = new ReadOnlyObjectWrapper<>();

    private static final Border HIGHLIGHTED_BORDER = new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(2)));

    private static final Border NORMAL_BORDER = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(4)));

    private static final Image IMAGE_CERCLE = new Image("o.png");
    private static final Image IMAGE_CROIX = new Image("x.png");

    @Nullable
    private Joueur status;

    @FXML
    private ImageView imageView;

    @FXML
    private Boite boite;

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

    public void setListener(@Nullable CaseClickListener listener) {
        this.listener = listener;
    }

    @FXML
    protected void handleMouseClicked() {
        if (status == null && listener != null) {
            listener.notifierCaseClicked(position.get());
        }
    }

    @FXML
    protected void handleMouseEntered() {
        if (status == null) boite.setBorder(HIGHLIGHTED_BORDER);
    }

    @FXML
    protected void handleMouseExited() {
        boite.setBorder(NORMAL_BORDER);
    }

    void setStatus(Joueur status) {
        this.status = status;

        if (status == null) {
            imageView.setImage(null);
        } else if (status == Joueur.CROIX) {
            imageView.setImage(IMAGE_CROIX);
            boite.setBorder(NORMAL_BORDER);
        } else {
            imageView.setImage(IMAGE_CERCLE);
            boite.setBorder(NORMAL_BORDER);
        }
    }

    @Nullable
    Joueur getStatus() {
        return status;
    }
}