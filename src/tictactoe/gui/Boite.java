package tictactoe.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tictactoe.CaseClickListener;
import tictactoe.Position;

public class Boite extends StackPane {
    private static final Border HIGHLIGHTED_BORDER = new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT));
    private static final Border NORMAL_BORDER = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT));

    private static final Image imageCroix = new Image("/x.png");
    private static final Image imageCercle = new Image("/o.png");

    public enum Status {
        VIDE,
        CROIX,
        CERCLE
    }

    @NotNull
    private Status status = Status.VIDE;

    private final ImageView imageView = new ImageView();

    Boite(@NotNull Position position, @Nullable CaseClickListener listener) {
        super();

        imageView.setPreserveRatio(true);
        imageView.setFitHeight(this.getHeight());
        imageView.setFitWidth(this.getWidth());
        this.getChildren().add(imageView);

        this.setMinSize(0,0);

        this.setBorder(NORMAL_BORDER);

        this.setOnMouseClicked(event -> {
            if (listener != null && status == Status.VIDE) {
                listener.notifierCaseClicked(position);
            }
        });

        this.setOnMouseEntered(event -> {
            if (status == Status.VIDE) this.setBorder(HIGHLIGHTED_BORDER);
        });

        this.setOnMouseExited(event -> this.setBorder(NORMAL_BORDER));
    }

    void setStatus(Status status) {
        this.status = status;

        switch (status) {
            case CERCLE:
                imageView.setImage(imageCercle);
                break;
            case CROIX:
                imageView.setImage(imageCroix);
                break;
            case VIDE:
                imageView.setImage(null);
                break;
        }
    }

    @NotNull
    Status getStatus() {
        return status;
    }
}