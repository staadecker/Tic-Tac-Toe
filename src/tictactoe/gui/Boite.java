package tictactoe.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tictactoe.CaseClickListener;
import tictactoe.Joueur;
import tictactoe.Position;

public class Boite extends Pane {
    private static final Border HIGHLIGHTED_BORDER = new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT));
    private static final Border NORMAL_BORDER = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT));

    private static final Image IMAGE_CERCLE = new Image("o.png");
    private static final Image IMAGE_CROIX = new Image("x.png");

    @Nullable
    private Joueur status;

    private final ImageView imageView = new ImageView();

    private final Position position;

    Boite(@NotNull Position position) {
        super();

        StackPane stackPane = new StackPane(imageView);

        stackPane.prefWidthProperty().bind(this.widthProperty());
        stackPane.prefHeightProperty().bind(this.heightProperty());

        this.getChildren().add(stackPane);
        this.setBorder(NORMAL_BORDER);

        this.position = position;

        this.imageView.setPreserveRatio(true);
        this.imageView.fitWidthProperty().bind(this.widthProperty());
        this.imageView.fitHeightProperty().bind(this.heightProperty());
    }

    void addListener(@NotNull CaseClickListener listener) {
        this.setOnMouseClicked(event -> {
            if (status == null) {
                listener.notifierCaseClicked(position);
            }
        });

        this.setOnMouseEntered(event -> {
            if (status == null) this.setBorder(HIGHLIGHTED_BORDER);
        });

        this.setOnMouseExited(event -> this.setBorder(NORMAL_BORDER));
    }

    void setStatus(Joueur status) {
        this.status = status;

        if (status == null) {
            imageView.setImage(null);
        } else if (status == Joueur.CROIX) {
            imageView.setImage(IMAGE_CROIX);
        } else {
            imageView.setImage(IMAGE_CERCLE);
        }
    }

    @Nullable
    Joueur getStatus() {
        return status;
    }
}