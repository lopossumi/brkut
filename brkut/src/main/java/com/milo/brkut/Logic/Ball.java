package com.milo.brkut.Logic;

import java.awt.Color;

/**
 * @author milo
 */
public class Ball extends GameObject {

    public Ball(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.color = Color.WHITE;
    }

    public void bounceHorizontal() {
        this.setVx(-this.getVx());
    }

    public void bounceVertical() {
        this.setVy(-this.getVy());
    }

}
