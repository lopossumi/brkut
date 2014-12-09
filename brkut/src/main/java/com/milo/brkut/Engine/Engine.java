package com.milo.brkut.Engine;

import com.milo.brkut.Logic.*;
import com.milo.brkut.Main.Config;
import com.milo.brkut.Main.HighscoreIO;
import java.awt.Color;
import java.awt.event.WindowEvent;

/**
 * @author milo
 */
public class Engine extends Thread {

    private GUI gui;
    private Logic logic;
    private boolean running;
    private SoundEngine sounds;

    public Engine(GUI gui, Logic logic) {
        this.gui = gui;
        this.logic = logic;
        this.running = true;
        this.sounds = new SoundEngine();
    }

    @Override
    public void run() {
        while (this.running) {
            update();
            draw();
            hold(1000 / 60);
        }

        // Running complete - we're done here. Close window.
        gui.getFrame().dispatchEvent(new WindowEvent(gui.getFrame(), WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Update the game logic.
     */
    public void update() {
        logic.step(this.gui.input().update());
        if (logic.getStatus() == GameStatusEnum.HIT) {
            this.sounds.hit();
        }
        if (logic.getStatus() == GameStatusEnum.WON) {
            doWin();
        }
        if (logic.getStatus() == GameStatusEnum.HIGHSCORE) {
            doHighScore();
        }
        if (logic.getStatus() == GameStatusEnum.GAMEOVER) {
            doGameOver();
        }
        if (logic.getStatus() == GameStatusEnum.DIED) {
            doDeath();
        }
    }

    /**
     * Draw updated game logic on screen.
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
        this.logic.reset();
    }

    /**
     * Play game over sound, animate paddle dying. TODO: - Show high score -
     * Show game over text
     */
    private void doGameOver() {
        this.sounds.died();
        animateDeath();
        drawHold(30);
        close();
    }

    /**
     * Play game over sound, animate paddle dying. TODO: - Show high score -
     * Show game over text
     */
    private void doHighScore() {
        this.sounds.died();
        animateDeath();
        drawHold(30);
        HighscoreIO.update(logic.getScore());
        close();
    }

    /**
     * Play death animation. Paddle turns red and changes shape.
     */
    private void animateDeath() {
        Player pOne = this.logic.getPlayerOne();
        for (int i = 0; i < 100; i++) {
            pOne.setColor(new Color(255, 200 - i * 2, 200 - i * 2));
            pOne.setWidth(pOne.getWidth() * 0.95);
            pOne.setHeight(pOne.getHeight() + 10 - i);
            hold(1000 / 60);
            draw();
        }
        drawHold(60);
    }

    /**
     * All bricks are destroyed. Something cool happens.
     */
    private void doWin() {
        draw();
        hold(2000);
        close();
    }

    /**
     * Holds for a number of frames, while updating the screen for animations.
     *
     * @param frames Number of frames to wait
     */
    private void drawHold(int frames) {
        for (int i = 0; i < frames; i++) {
            hold(1000 / 60);
            draw();
        }
    }
}
