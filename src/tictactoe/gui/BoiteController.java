package tictactoe.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tictactoe.ClickListener;
import tictactoe.Jeu;
import tictactoe.StatusJeu;
import tictactoe.util.Position;

/**
 * Une boite (case) du plateau de jeu tic-tac-toe pour l'interface graphique
 */
public class BoiteController {
    private static final Image IMAGE_X = new Image("/image/x.png");
    private static final Image IMAGE_O = new Image("/image/o.png");

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
    private Pane boite;

    @FXML
    private ImageView image;

    private Position position;

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


    public BoiteController(Jeu jeu, Position position) {
        this.listener = jeu;
        this.jeu = jeu;
        this.position = position;

        //Si le status du jeu n'est plus EN_PARTIE, shouldHighlight = false
        jeu.jeuStatusProperty().addListener(
                (observable, oldValue, newValue) -> shouldHighlight = newValue == StatusJeu.EN_PARTIE
        );
    }

    @FXML
    private void initialize(){
        status.bind(jeu.boiteStatusProperty(position));

        //Quand le status change changer la boite
        status.addListener(
                (observable, oldValue, newValue) -> {
                    switch (newValue) {
                        case VIDE:
                            image.setImage(null); //Enelever l'image
                            break;
                        case CROIX:
                            // Mettre l'image X et enlever la bordure-hover
                            image.setImage(IMAGE_X);
                            boite.getStyleClass().setAll("bordure-normale");
                            break;
                        case CERCLE:
                            // Mettre l'image O et enlever la bordure-hover
                            image.setImage(IMAGE_O);
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
            listener.notifierBoiteClicked(position);
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