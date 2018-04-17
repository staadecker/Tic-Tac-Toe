package tictactoe.gui;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tictactoe.Position;

import java.io.IOException;

@SuppressWarnings("WeakerAccess")
public class Boite extends Pane {
    @NotNull
    private final ReadOnlyObjectWrapper<CaseStatus> status = new ReadOnlyObjectWrapper<>();

    @NotNull
    private final Position position;

    @FXML
    private Boite boite;

    @Nullable
    private Boite.ClickListener listener;

    Boite(@NotNull Position position) {
        this.position = position;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/boite.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        status.addListener((observable, oldValue, newValue) -> {
            if (newValue == CaseStatus.VIDE) {
                boite.setStyle("-fx-background-image: none;");
            } else if (newValue == CaseStatus.CROIX) {
                boite.setStyle("-fx-background-image: url(x.png);");
                boite.getStyleClass().setAll("boite-hover");
            } else {
                boite.setStyle("-fx-background-image: url(o.png)");
                boite.getStyleClass().setAll("boite-normal");
            }
        });
    }

    @NotNull
    ReadOnlyObjectWrapper<CaseStatus> statusProperty() {
        return status;
    }

    void setListener(@Nullable Boite.ClickListener listener) {
        this.listener = listener;
    }

    @FXML
    protected void handleMouseClick() {
        if (status.get().equals(CaseStatus.VIDE) && listener != null) {
            listener.notifierCaseClicked(position);
        }
    }

    @FXML
    protected void handleMouseEnter() {
        if (status.get().equals(CaseStatus.VIDE)) boite.getStyleClass().setAll("boite-hover");
    }

    @FXML
    protected void handleMouseExit() {
        boite.getStyleClass().setAll("boite-normal");
    }

    public interface ClickListener {
        void notifierCaseClicked(Position position);
    }

    public enum CaseStatus {
        CROIX,
        CERCLE,
        VIDE
    }
}