package tictactoe.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tictactoe.Position;

import java.io.IOException;

/**
 * Une boite ou case du plateau de jeu tic-tac-toe
 */
public class Boite extends Pane {
    /**
     * Les differents status de la boite possible
     * Soit vide avec un X ou avec un O
     */
    public enum BoiteStatus {
        CROIX,
        CERCLE,
        VIDE
    }

    /**
     * Interface qui definit un listener pour notifier quand une boite est appuyé
     */
    public interface ClickListener {
        void notifierBoiteClicked(Position position);
    }

    /**
     * Status present de la boite
     * Commence étant vide
     */
    @NotNull
    private final SimpleObjectProperty<BoiteStatus> status = new SimpleObjectProperty<>(BoiteStatus.VIDE);

    /**
     * Position de la boite sur le tableau
     */
    @NotNull
    private final Position position;

    /**
     * Listener à notifier quand la boite est appuyée
     */
    @Nullable
    private Boite.ClickListener listener;

    Boite(@NotNull Position position) {
        this.position = position;

        status.addListener(
                (observable, oldValue, newValue) -> {
                    switch (newValue) {
                        case VIDE:
                            this.setStyle("-fx-background-image: none;");
                            break;
                        case CROIX:
                            this.setStyle("-fx-background-image: url(x.png);");
                            this.getStyleClass().setAll("boite-normal");
                            break;
                        case CERCLE:
                            this.setStyle("-fx-background-image: url(o.png)");
                            this.getStyleClass().setAll("boite-normal");
                    }
                }
        );

        //Attacher l'objet au fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/boite.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Ajoute un listener qui va être appelé quand la boite est clické
     * @param listener le listener
     */
    void setListener(@Nullable Boite.ClickListener listener) {
        this.listener = listener;
    }

    /**
     * @return proprieté qui définit le status de la boite (vide, O ou X)
     */
    @NotNull
    SimpleObjectProperty<BoiteStatus> statusProperty() {
        return status;
    }

    /**
     * Si un listener est designé et la boite est vide notifier le listener
     */
    @FXML
    protected void handleMouseClick() {
        if (status.get().equals(BoiteStatus.VIDE) && listener != null) {
            listener.notifierBoiteClicked(position);
        }
    }

    /**
     * Si la boite est vide la surligner
     */
    @FXML
    protected void handleMouseEnter() {
        if (status.get().equals(BoiteStatus.VIDE)) this.getStyleClass().setAll("boite-hover");
    }

    /**
     * Remettre la boite à normale quand le cursor sort
     */
    @FXML
    protected void handleMouseExit() {
        this.getStyleClass().setAll("boite-normal");
    }
}