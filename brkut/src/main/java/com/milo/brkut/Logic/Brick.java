package com.milo.brkut.Logic;

import java.awt.Color;

/**
 * @author milo
 */
public class Brick extends GameObject {

    private int hitpoints;
    private boolean alive;

    public Brick(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.hitpoints = 2;
        this.color = Color.GREEN;
        this.alive = true;
    }

    public void damage(int amount) {
        this.hitpoints -= amount;
        this.color = Color.RED;
        if (this.hitpoints <= 0) {
            this.alive = false;
        }
    }

    public boolean isAlive() {
        return this.alive;
    }

    public int hp() {
        return this.hitpoints;
    }
}
