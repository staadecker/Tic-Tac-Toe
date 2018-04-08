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

    private static final Image IMAGE_CERCLE = new Image("/o.png");
    private static final Image IMAGE_CROIX = new Image("/x.png");

    public enum Status {
        VIDE,
        CROIX,
        CERCLE
    }

    @NotNull
    private Status status = Status.VIDE;

    private final ImageView imageView = new ImageView();

    private final Position position;

    Boite(@NotNull Position position) {
        super();

        this.getChildren().add(imageView);
        this.setBorder(NORMAL_BORDER);

        this.position = position;
    }

    void addListener(@NotNull CaseClickListener listener){
        this.setOnMouseClicked(event -> {
            if (status == Status.VIDE) {
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
                imageView.setImage(IMAGE_CERCLE);
            case CROIX:
                imageView.setImage(IMAGE_CROIX);
            case VIDE:
                imageView.setImage(null);
        }
    }

    @NotNull
    Status getStatus() {
        return status;
    }
}