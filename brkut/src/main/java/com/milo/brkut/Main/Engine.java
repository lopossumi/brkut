package com.milo.brkut.Main;

import com.milo.brkut.Logic.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author milo
 */
public class Engine extends Thread {

    private GUI gui;
    private Arena arena;
    private boolean running;
    private SoundEngine sounds;

    public Engine(GUI gui, Arena arena) {
        this.gui = gui;
        this.arena = arena;
        this.running = true;
        this.sounds = new SoundEngine();
    }

    @Override
    public void run() {
        while (this.running) {
            update();
            draw();
            hold(1000/60);
        }
    }

    /**
     * Update the game arena.
     */
    public void update() {
        arena.step(this.gui.input().update());
        if (arena.getStatus() == GameStatus.HIT) {
            this.sounds.hit();
        }
        if (arena.getStatus() == GameStatus.DIED){
            doDeath();
        }
        if (arena.getStatus() == GameStatus.GAMEOVER){
            doGameOver();
        }
    }

    /**
     * Draw updated game arena on screen.
     */
    public void draw() {
        gui.draw();
    }

    public void close() {
        running = false;
    }

    /**
     * Wait for the next calculation step.
     */
    public void hold(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void doDeath() {
        this.sounds.died();
        hold(2000);
        this.arena.reset();
    }

    private void doGameOver() {
        this.sounds.died();
        hold(2000);
        this.running = false;
    }
}
