package com.milo.brkut.Main;

import com.milo.brkut.Logic.*;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;
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
            draw();
            hold(1000 / 60);
            update();
        }
        
        // Close window
        gui.getFrame().dispatchEvent(new WindowEvent(gui.getFrame(), WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Update the game arena.
     */
    public void update() {
        arena.step(this.gui.input().update());
        if (arena.getStatus() == GameStatus.HIT) {
            this.sounds.hit();
        }
        if (arena.getStatus() == GameStatus.DIED) {
            doDeath();
        }
        if (arena.getStatus() == GameStatus.GAMEOVER) {
            doGameOver();
        }
    }

    /**
     * Draw updated game arena on screen.
     */
    public void draw() {
        gui.draw();
    }

    /**
     * Stop the engine.
     */
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

    /**
     * Play death sound, animate paddle dying, reset.
     */
    private void doDeath() {
        this.sounds.died();
        animateDeath();
        hold(500);
        this.arena.reset();
    }

    /**
     * Play game over sound, animate paddle dying. 
     * TODO:
     * - Show high score
     * - Show game over text
     */
    private void doGameOver() {
        this.sounds.died();
                animateDeath();

        hold(500);
        close();
    }

    /**
     * Play death animation. Paddle turns red and diminishes in size.
     */
    private void animateDeath() {
        Player pOne = this.arena.getPlayerOne();
        for (int i = 0; i < 100; i++) {
            pOne.setColor(new Color(255, 200 - i * 2, 200 - i * 2));
            pOne.setWidth(pOne.getWidth() * 0.95);
            hold(1000 / 60);
            draw();
        }
        hold(500);
        pOne.setWidth(100);
        pOne.setColor(Color.WHITE);
    }
}
