package com.milo.brkut.logic;

import com.milo.brkut.main.Config;
import java.awt.Color;

/**
 * @author milo
 */
public class Ball extends GameObject {

    public Ball(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.color = Config.BALL_COLOR;
        this.setMaxSpeed(Config.BALL_MAXSPEED);
    }

    public void bounceHorizontal() {
        this.setVx(-this.getVx());
    }

    public void bounceVertical() {
        this.setVy(-this.getVy());
    }

}
