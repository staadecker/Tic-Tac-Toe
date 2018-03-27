package tictactoe.gui;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import tictactoe.CaseClickListener;

public class Case extends StackPane implements EventHandler<javafx.scene.input.MouseEvent> {
    public enum Status {
        VIDE,
        X,
        O
    }

    private Status status = Status.VIDE;

    private final CaseClickListener listener;

    Case(CaseClickListener listener) {
        super();

        this.listener = listener;

        this.setOnMouseClicked(this);
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

    Status getStatus() {
        return status;
    }

    @Override
    public void handle(MouseEvent event) {
        if (listener != null) {
            listener.notifierCaseClicked(this);
        }
    }

    private void mettreImage(String nomDuFichier) {
        ImageView imageView = new ImageView(this.getClass().getResource(nomDuFichier).toString());
        imageView.setPreserveRatio(true);
        this.getChildren().addAll(imageView);
    }
}
