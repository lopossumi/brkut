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

    @Override
    public void damage(int amount) {
        if (amount>this.hitpoints)
            this.hitpoints = 0;
        else 
            this.hitpoints-=amount;
        
        updateColor();
    }

    private void updateColor() {
        if (this.hitpoints > 1) {
            this.color = Color.GREEN;
        }
        else
            this.color = Color.RED;
    }

    public boolean isAlive() {
        return (this.hitpoints > 0);
    }

    public int getHitpoints() {
        return this.hitpoints;
    }
}
