package tictactoe;

import tictactoe.util.Position;

/**
 * Interface qui definit un listener pour notifier quand une boite est appuyé
 */
public interface ClickListener {
    void notifierBoiteClicked(Position position);
}
