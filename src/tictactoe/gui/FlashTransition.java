package tictactoe.gui;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class FlashTransition extends Transition {
    private final Region node;

    FlashTransition(Region node) {
        this.node = node;

        setCycleDuration(Duration.millis(1000));
        setInterpolator(Interpolator.EASE_OUT);
    }

    @Override
    protected void interpolate(double frac) {
        Color vColor = new Color(0, 1, 0, 1 - frac);
        node.setBackground(new Background(new BackgroundFill(vColor, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
