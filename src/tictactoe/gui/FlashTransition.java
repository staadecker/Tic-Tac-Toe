/*
 * MIT License
 *
 * Copyright (c) 2018 Martin Staadecker
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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

/**
 * Transition utilisé pour flasher du vert quand le pointage change
 */
class FlashTransition extends Transition {
    /**
     * La region sur laquel la transition s'applique
     */
    private final Region region;

    FlashTransition(Region region) {
        this.region = region;

        setCycleDuration(Duration.millis(1000));
        setInterpolator(Interpolator.EASE_OUT);
    }

    /**
     * Appelé répétitivement à travers la transition
     *
     * @param frac la fraction de temps qui a été écoulé dans la transition (entre 0 et 1)
     */
    @Override
    protected void interpolate(double frac) {
        region.setBackground(new Background(
                new BackgroundFill(
                        new Color(0, 1, 0, 1 - frac), // Une couleur qui devient de plus en plus transparente au fur et mesure que frac augmente
                        CornerRadii.EMPTY, Insets.EMPTY
                )
        ));
    }
}
