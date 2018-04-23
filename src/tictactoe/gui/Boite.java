package tictactoe.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tictactoe.ClickListener;
import tictactoe.util.Position;

import java.io.IOException;

/**
 * Une boite (case) du plateau de jeu tic-tac-toe pour l'interface graphique
 */
public class Boite extends Pane {
    /**
     * Les differents status possible pour la boite
     * Soit vide, avec un X, ou avec un O
     */
    public enum Status {
        CROIX,
        CERCLE,
        VIDE
    }

    /**
     * Status de la boite
     * La valeur par défaut est vide
     */
    @NotNull
    private final SimpleObjectProperty<Status> status = new SimpleObjectProperty<>(Status.VIDE);

    /**
     * Position de la boite sur le tableau
     */
    @NotNull
    private final Position position;

    /**
     * Listener à notifier quand la boite est appuyée
     */
    @Nullable
    private ClickListener listener;

    /**
     * @param position la position the la boite
     */
    Boite(@NotNull Position position) {
        this.position = position;

        //Quand le status change...
        status.addListener(
                (observable, oldValue, newValue) -> {
                    switch (newValue) {
                        case VIDE:
                            this.setStyle("-fx-background-image: none;"); //Enelever l'image
                            break;
                        case CROIX:
                            // Mettre l'image X et enlever la bordure-hover
                            this.setStyle("-fx-background-image: url(x.png);");
                            this.getStyleClass().setAll("bordure-normale");
                            break;
                        case CERCLE:
                            // Mettre l'image O et enlever la bordure-hover
                            this.setStyle("-fx-background-image: url(o.png)");
                            this.getStyleClass().setAll("bordure-normale");
                    }
                }
        );

        //Attacher l'objet au ficihier fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/boite.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * @param listener le listener à notifier quand la boite est appuyé
     */
    void attachListener(ClickListener listener){
        this.listener = listener;
    }

    /**
     * Appelé quand la boite a été clické
     * Si un listener est designé et la boite est vide notifier le listener que la boite a été clické
     */
    @FXML
    private void handleMouseClick() {
        if (status.get().equals(Status.VIDE) && listener != null) {
            listener.notifierBoiteClicked(position);
        }
    }

    /**
     * Appelé quand le curseur rentre dans la boite
     * Si la boite est vide la surligner
     */
    @FXML
    private void handleMouseEnter() {
        if (status.get().equals(Status.VIDE)) this.getStyleClass().setAll("bordure-hover");
    }

    /**
     * Appelé quand le curseur quitte la boite
     */
    @FXML
    private void handleMouseExit() {
        this.getStyleClass().setAll("bordure-normale");
    }

    @NotNull
    SimpleObjectProperty<Status> statusProperty() {
        return status;
    }
}