package com.milo.brkut.Logic;

/**
 * @author milo
 */
public class Ball extends GameObject {

    public Ball(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public void bounceHorizontal() {
        this.setVx(-this.getVx());
    }

    public void bounceVertical() {
        this.setVy(-this.getVy());
    }

}
