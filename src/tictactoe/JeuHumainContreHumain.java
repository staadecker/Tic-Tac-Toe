package tictactoe;

import tictactoe.gui.Boite;
import tictactoe.util.Position;

public class JeuHumainContreHumain extends JeuBase implements Boite.ClickListener {
    @Override
    public void notifierBoiteClicked(Position position) {
        jouer(position);
    }
}