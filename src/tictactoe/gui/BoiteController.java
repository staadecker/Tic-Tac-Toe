package tictactoe.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tictactoe.ClickListener;
import tictactoe.Jeu;
import tictactoe.StatusJeu;

/**
 * Une boite (case) du plateau de jeu tic-tac-toe pour l'interface graphique
 */
public class BoiteController {
    /**
     * Les differents status possible pour la boite
     * Soit vide, avec un X, ou avec un O
     */
    public enum Status {
        CROIX,
        CERCLE,
        VIDE
    }

    @FXML
    private Boite boite;

    /**
     * Status de la boite
     * La valeur par défaut est vide
     */
    @NotNull
    private final SimpleObjectProperty<Status> status = new SimpleObjectProperty<>(Status.VIDE);

    private boolean shouldHighlight = true;

    /**
     * Listener à notifier quand la boite est appuyée
     */
    @Nullable
    private ClickListener listener;

    private final Jeu jeu;


    public BoiteController(Jeu jeu) {
        this.listener = jeu;
        this.jeu = jeu;

        //Si le status du jeu n'est plus EN_PARTIE, shouldHighlight = false
        jeu.jeuStatusProperty().addListener(
                (observable, oldValue, newValue) -> shouldHighlight = newValue == StatusJeu.EN_PARTIE
        );
    }

    @FXML
    private void initialize(){
        status.bind(jeu.boiteStatusProperty(boite.getPosition()));

        //Quand le status change changer la boite
        status.addListener(
                (observable, oldValue, newValue) -> {
                    switch (newValue) {
                        case VIDE:
                            boite.setStyle("-fx-background-image: none;"); //Enelever l'image
                            break;
                        case CROIX:
                            // Mettre l'image X et enlever la bordure-hover
                            boite.setStyle("-fx-background-image: url(image/x.png);");
                            boite.getStyleClass().setAll("bordure-normale");
                            break;
                        case CERCLE:
                            // Mettre l'image O et enlever la bordure-hover
                            boite.setStyle("-fx-background-image: url(image/o.png)");
                            boite.getStyleClass().setAll("bordure-normale");
                    }
                }
        );
    }

    /**
     * Appelé quand la boite a été clické
     * Si un listener est designé et la boite est vide notifier le listener que la boite a été clické
     */
    @FXML
    private void handleMouseClick() {
        if (status.get().equals(Status.VIDE) && listener != null) {
            listener.notifierBoiteClicked(boite.getPosition());
        }
    }

    /**
     * Appelé quand le curseur rentre dans la boite
     * Si la boite est vide la surligner
     */
    @FXML
    private void handleMouseEnter() {
        if (status.get().equals(Status.VIDE) && shouldHighlight) boite.getStyleClass().setAll("bordure-hover");
    }

    /**
     * Appelé quand le curseur quitte la boite
     */
    @FXML
    private void handleMouseExit() {
        boite.getStyleClass().setAll("bordure-normale");
    }
}