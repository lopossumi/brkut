package com.milo.brkut.logic;

import com.milo.brkut.main.Config;
import java.awt.Color;

/**
 * The bricks.
 * @author milo
 */
public class Brick extends GameObject {

    private int hitpoints;
    private boolean alive;

    public Brick(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.hitpoints = Config.BRICK_HITPOINTS;
        this.color = Color.GREEN;
        this.alive = true;
    }

    /**
     * Damages the brick reducing its hitpoints towards zero.
     * @param amount Amount of damage inflicted.
     */
    @Override
    public void damage(int amount) {
        if (amount > this.hitpoints) {
            this.hitpoints = 0;
        } else {
            this.hitpoints -= amount;
        }

        updateColor();
    }

    /**
     * Updates the color to RED if hitpoints are 1.
     */
    private void updateColor() {
        if (this.hitpoints > 1) {
            this.color = Color.GREEN;
        } else {
            this.color = Color.RED;
        }
    }

    /**
     * Returns true if more than zero hitpoints.
     * @return true for living bricks
     */
    @Override
    public boolean isAlive() {
        return (this.hitpoints > 0);
    }

    public int getHitpoints() {
        return this.hitpoints;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
