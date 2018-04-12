package tictactoe;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    PlateauDeJeu plateauDeJeu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JeuxHumainContreHumain jeux = new JeuxHumainContreHumain(plateauDeJeu);
    }
}
