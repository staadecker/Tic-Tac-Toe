package tictactoe.gui;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tictactoe.CaseStatus;
import tictactoe.CaseClickListener;
import tictactoe.Position;

import java.io.IOException;

@SuppressWarnings("WeakerAccess")
public class Case extends Pane implements ChangeListener<CaseStatus> {
    @NotNull
    private final ReadOnlyObjectWrapper<CaseStatus> status = new ReadOnlyObjectWrapper<>();

    @NotNull
    private final Position position;

    @FXML
    private Case boite;

    @Nullable
    private CaseClickListener listener;

    Case(@NotNull Position position) {
        this.position = position;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/boite.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        status.addListener(this);
    }

    void setListener(@Nullable CaseClickListener listener) {
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

    @Override
    public void changed(ObservableValue<? extends CaseStatus> observable, CaseStatus oldValue, CaseStatus newValue) {
        if (newValue == CaseStatus.VIDE) {
            boite.setStyle("-fx-background-image: none;");
        } else if (newValue == CaseStatus.CROIX) {
            boite.setStyle("-fx-background-image: url(x.png);");
            boite.getStyleClass().setAll("boite-hover");
        } else {
            boite.setStyle("-fx-background-image: url(o.png)");
            boite.getStyleClass().setAll("boite-normal");
        }
    }

    @NotNull
    ReadOnlyObjectWrapper<CaseStatus> statusProperty() {
        return status;
    }
}