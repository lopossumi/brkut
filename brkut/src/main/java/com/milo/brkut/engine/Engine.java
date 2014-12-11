package com.milo.brkut.engine;

import com.milo.brkut.logic.Player;
import com.milo.brkut.logic.Logic;
import com.milo.brkut.logic.GameStatusEnum;
import com.milo.brkut.main.HighscoreIO;
import java.awt.Color;
import java.awt.event.WindowEvent;

/**
 * Game engine.
 * @author milo
 */
public class Engine extends Thread {

    private GUI gui;
    private Logic logic;
    private boolean running;
    private SoundEngine sounds;

    /**
     * Engine constructor. Also creates a SoundEngine for sound effects.
     *
     * @param gui Graphical user interface
     * @param logic Game logic
     */
    public Engine(GUI gui, Logic logic) {
        this.gui = gui;
        this.logic = logic;
        this.running = false;
        this.sounds = new SoundEngine();
    }

    /**
     * Starts the game engine and the update-draw-hold loop.
     */
    @Override
    public void run() {
        this.running = true;
        while (this.running) {
            update();
            draw();
            hold(1000 / 60);
        }

        // Running complete - we're done here. Close window.
        gui.getFrame().dispatchEvent(new WindowEvent(gui.getFrame(), WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Update the game logic and decide on an action based on the returned
     * GameStatus.
     */
    public void update() {
        GameStatusEnum status = logic.step(this.gui.input().update());

        if (status == GameStatusEnum.HIT) {
            this.sounds.hit1();
        }
        if (status == GameStatusEnum.WALLHIT) {
            this.sounds.hit2();
        }
        if (status == GameStatusEnum.WON || status == GameStatusEnum.WONHIGHSCORE) {
            doWin();
        }
        if (status == GameStatusEnum.HIGHSCORE) {
            doHighscore();
        }
        if (status == GameStatusEnum.GAMEOVER) {
            doGameOver();
        }
        if (status == GameStatusEnum.DIED) {
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
     *
     * @param ms milliseconds to wait
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
     * Play game over sound, animate paddle dying and ask for a new game.
     */
    private void doGameOver() {
        this.sounds.gameover();
        animateDeath();
        int tryAgain = tryAgainQuery();

        // Quit or start new game.
        if (tryAgain == 0) {
            close();
        } else {
            int highScore = this.logic.getHighscore();
            this.logic = new Logic(highScore);
            this.gui.setLogic(this.logic);
        }
    }

    /**
     * Waits for keyboard input (Y/N)
     *
     * @return 1 to play again, 0 to quit.
     */
    private int tryAgainQuery() {
        int playAgain = -1;
        while (playAgain == -1) {
            boolean[] kp = this.gui.input().update();
            if (kp[KeypressEnum.N.getValue()]) {
                playAgain = 0;
            }
            if (kp[KeypressEnum.Y.getValue()]) {
                playAgain = 1;
            }
            drawHold(1);
        }
        return playAgain;
    }

    /**
     * Play game over sound, animate paddle dying and ask for a new game while
     * showing the animated HIGH SCORE text.
     */
    private void doHighscore() {
        this.sounds.gameover();
        animateDeath();
        HighscoreIO.update(logic.getScore());
        int tryAgain = tryAgainQuery();

        // Quit or start new game.
        if (tryAgain == 0) {
            close();
        } else {
            int highScore = this.logic.getScore();
            this.logic = new Logic(highScore);
            this.gui.setLogic(this.logic);
        }
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
    }

    /**
     * All bricks are destroyed. Show game over or high score.
     */
    private void doWin() {
        if (this.logic.getScore() > this.logic.getHighscore()) {
            doHighscore();
        } else {
            doGameOver();
        }
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
