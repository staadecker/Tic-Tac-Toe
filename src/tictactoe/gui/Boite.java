package tictactoe.gui;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tictactoe.CaseClickListener;

public class Boite extends StackPane implements EventHandler<javafx.scene.input.MouseEvent> {

    private final Border HIGHLIGHTED_BORDER = new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT));
    private final Border NORMAL_BORDER = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT));


    public enum Status {
        VIDE,
        X,
        O
    }

    private Status status = Status.VIDE;

    @Nullable
    private final CaseClickListener listener;
    private final int id;

    Boite(int id, @Nullable CaseClickListener listener) {
        super();

        this.id = id;
        this.listener = listener;

        this.setBorder(NORMAL_BORDER);

        this.setOnMouseClicked(this);
        this.setOnMouseEntered(event -> this.setBorder(HIGHLIGHTED_BORDER));
        this.setOnMouseExited(event -> this.setBorder(NORMAL_BORDER));
    }

    void setStatus(Status status) {
        this.status = status;

        switch (status) {
            case O:
                mettreImage("/x.png");
            case X:
                mettreImage("/o.png");
            case VIDE:
                this.getChildren().clear();
        }
    }

    @NotNull
    Status getStatus() {
        return status;
    }

    @Override
    public void handle(MouseEvent event) {
        if (listener != null && status == Status.VIDE) {
            listener.notifierCaseClicked(id);
        }
    }

    private void mettreImage(String nomDuFichier) {
        ImageView imageView = new ImageView(this.getClass().getResource(nomDuFichier).toString());
        imageView.setPreserveRatio(true);
        this.getChildren().addAll(imageView);
    }
}