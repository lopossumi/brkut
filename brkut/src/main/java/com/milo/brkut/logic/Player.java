package com.milo.brkut.logic;

import com.milo.brkut.main.Config;
import java.awt.Color;

/**
 * @author milo
 */
public class Player extends GameObject {

    private int lives;
    private boolean alive;

    public Player(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.lives = Config.PLAYER_LIVES;
        this.color = Color.WHITE;
        this.alive = true;
        this.setMaxSpeed(Config.PLAYER_MAXSPEED);
    }

    public int getLives() {
        return this.lives;
    }

    public void addLife() {
        this.lives++;
    }

    public void kill() {
        this.alive = false;
        this.lives--;
    }

    public void respawn(double x, double y) {
        this.alive = true;
        this.stop();
        this.moveTo(x, y);
        this.setWidth(Config.PLAYER_WIDTH);
        this.setHeight(Config.PLAYER_HEIGHT);
        this.setColor(Config.PLAYER_COLOR);
    }

    @Override
    boolean isAlive() {
        return this.alive;
    }

}
