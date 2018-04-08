package tictactoe.gui;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tictactoe.CaseClickListener;
import tictactoe.Position;

public class Boite extends StackPane {
    private static final Border HIGHLIGHTED_BORDER = new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT));
    private static final Border NORMAL_BORDER = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT));

    public enum Status {
        VIDE,
        CROIX,
        CERCLE
    }

    @NotNull
    private Status status = Status.VIDE;

    Boite(@NotNull Position position, @Nullable CaseClickListener listener) {
        super();

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
                mettreImage("/x.png");
            case CROIX:
                mettreImage("/o.png");
            case VIDE:
                this.getChildren().clear();
        }
    }

    @NotNull
    Status getStatus() {
        return status;
    }

    private void mettreImage(String nomDuFichier) {
        ImageView imageView = new ImageView(this.getClass().getResource(nomDuFichier).toString());
        imageView.setPreserveRatio(true);
        this.getChildren().addAll(imageView);
    }
}