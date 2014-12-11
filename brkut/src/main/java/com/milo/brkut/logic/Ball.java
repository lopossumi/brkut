package com.milo.brkut.logic;

import com.milo.brkut.main.Config;

/**
 * The ball.
 * @author milo
 */
public class Ball extends GameObject {

    public Ball(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.color = Config.BALL_COLOR;
        this.setMaxSpeed(Config.BALL_MAXSPEED);
    }

    /**
     * Bounces the ball horizontally (direction flips from right to left or vice versa).
     */
    public void bounceHorizontal() {
        this.setVx(-this.getVx());
    }

    /**
     * Bounces the ball vertically (direction flips from up to down or vice versa).
     */
    public void bounceVertical() {
        this.setVy(-this.getVy());
    }

}
