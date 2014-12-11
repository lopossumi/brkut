package com.milo.brkut.engine;

import com.milo.brkut.logic.GameObject;
import com.milo.brkut.logic.Logic;
import com.milo.brkut.logic.GameStatusEnum;
import com.milo.brkut.main.Config;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * A panel contains all the graphical objects to draw onto screen.
 *
 * @author milo
 */
public class Panel extends JPanel {

    private Logic logic;
    private int tick;
    private Graphics g;

    /**
     * Creates a new Panel and initializes the tick.
     *
     * @param logic
     */
    public Panel(Logic logic) {
        this.logic = logic;
        this.tick = 0;
    }

    /**
     * Sets a new game logic and resets the tick.
     *
     * @param logic
     */
    public void setLogic(Logic logic) {
        this.logic = logic;
        this.tick = 0;
    }

    /**
     * Adds one to the tick variable. (resets at 360: think circle)
     */
    private void tick() {
        if (this.tick > 360) {
            this.tick = 0;
        } else {
            this.tick++;
        }
    }

    /**
     * Paints all the objects onto the panel.
     *
     * @param g Graphics object
     */
    @Override
    public void paint(Graphics g) {
        this.g = g;
        tick();
        super.paint(g);
        this.setBackground(new Color(30, 30, 30));
        drawGrid();
        drawSineWave();

        if (logic.getStatus() == GameStatusEnum.START) {
            drawStart();
        }
        if (logic.getStatus() == GameStatusEnum.GAMEOVER) {
            drawGameOver();
        }
        if (logic.getStatus() == GameStatusEnum.HIGHSCORE) {
            drawNewHighscore();
        }
        if (logic.getStatus() == GameStatusEnum.WON) {
            drawWon();
        }
        if (logic.getStatus() == GameStatusEnum.WONHIGHSCORE) {
            drawWonHighscore();
        }

        drawScore();
        drawLives();
        drawMultiplier();
        drawHighscore();

        // Draw GameObjects
        for (GameObject brick : logic.getBricks()) {
            drawGameObject(brick);
        }
        drawGameObject(logic.getPlayerOne());
        drawGameObject(logic.getBall());
        getToolkit().sync();
    }

    /**
     * Draws a rectangle for a game object according to its dimensions and
     * position.
     *
     * @param o The game object to be drawn.
     */
    public void drawGameObject(GameObject o) {
        if (o.getWidth() > 1) {
            g.setColor(o.getColor());
            g.fill3DRect(
                    (int) (o.getX() - o.getWidth() / 2),
                    (int) (o.getY() - o.getHeight() / 2),
                    (int) o.getWidth(),
                    (int) o.getHeight(),
                    true);
        }
    }

    /**
     * Draws the score indicator.
     */
    private void drawScore() {
        g.setFont(new Font("default", Font.BOLD, 16));
        g.setColor(Color.GREEN);
        char[] score = ("SCORE  " + String.valueOf(logic.getScore())).toCharArray();
        g.drawChars(score, 0, score.length, 400, 30);
    }

    /**
     * Draws the lives indicator.
     */
    private void drawLives() {
        g.setFont(new Font("default", Font.BOLD, 16));
        g.setColor(Color.GREEN);
        char[] lives = ("LIVES  " + String.valueOf(logic.getPlayerOne().getLives())).toCharArray();
        g.drawChars(lives, 0, lives.length, 30, 30);
    }

    /**
     * Draws the bonus multiplier indicator.
     */
    private void drawMultiplier() {
        g.setFont(new Font("default", Font.BOLD, 16));
        g.setColor(Color.GREEN);
        char[] multiplier = ("BONUS  " + String.format("%.2g%n", logic.getMultiplier()) + "x").toCharArray();
        g.drawChars(multiplier, 0, multiplier.length, 150, 30);
    }

    /**
     * Draws the highscore indicator.
     */
    private void drawHighscore() {
        g.setFont(new Font("default", Font.BOLD, 16));
        g.setColor(Color.GREEN);
        char[] highscore = ("HIGH SCORE  " + String.valueOf(logic.getHighscore())).toCharArray();
        g.drawChars(highscore, 0, highscore.length, 600, 30);
    }

    /**
     * Draws "PLAYER ONE GET READY" & "PRESS SPACE TO LAUNCH".
     */
    private void drawStart() {
        g.setFont(new Font("default", Font.BOLD, 40));
        g.setColor(Color.GREEN);
        char[] start = ("PLAYER ONE GET READY...").toCharArray();
        g.drawChars(start, 0, start.length, 130, 350);
        g.setFont(new Font("default", Font.BOLD, 30));

        // Animate color
        g.setColor(animateColor());
        start = ("(PRESS SPACE TO LAUNCH)").toCharArray();
        g.drawChars(start, 0, start.length, 180, 400);
    }

    /**
     * Creates a new color based on the tick value for effects.
     */
    private Color animateColor() {
        return new Color(
                (int) (127 + 127 * Math.sin(this.tick / 17)),
                (int) (127 + 127 * Math.sin(this.tick / 11)),
                (int) (127 + 127 * Math.sin(this.tick / 19)));

    }

    /**
     * Draws "GAME OVER" text and calls drawYesNo().
     */
    private void drawGameOver() {
        g.setFont(new Font("default", Font.BOLD, 40));
        g.setColor(Color.GREEN);
        char[] gameover = ("G A M E   O V E R").toCharArray();
        g.drawChars(gameover, 0, gameover.length, 230, 350);
        drawYesNo();
    }

    /**
     * Draws "HIGHSCORE" text and calls drawYesNo().
     */
    private void drawNewHighscore() {
        g.setFont(new Font("default", Font.BOLD, 40));
        g.setColor(animateColor());
        char[] gameover = ("H I G H   S C O R E !").toCharArray();
        g.drawChars(gameover, 0, gameover.length, 230, 350);
        drawYesNo();
    }

    /**
     * Draws a "TRY AGAIN (Y/N)?" query.
     */
    private void drawYesNo() {
        g.setFont(new Font("default", Font.BOLD, 40));
        g.setColor(Color.GREEN);
        char[] yesNo = ("TRY AGAIN? (Y/N)").toCharArray();
        g.drawChars(yesNo, 0, yesNo.length, 230, 400);
    }

    /**
     * Draws an animated grid on the background.
     */
    private void drawGrid() {
        g.setColor(new Color(0, Math.abs(tick / 2 - 90), 0));

        int gridWidth = 40;
        for (int x = 0; x < Config.ARENA_WIDTH; x = x + gridWidth) {
            g.drawLine(x, 0, x, Config.ARENA_HEIGHT);
        }
        for (int y = 0; y < Config.ARENA_HEIGHT; y = y + gridWidth) {
            g.drawLine(0, y, Config.ARENA_WIDTH, y);
        }
    }

    /**
     * Draws an animated sine wave, where the amplitude increases with bonus
     * multiplier.
     */
    private void drawSineWave() {
        g.setColor(Color.WHITE);

        int yMid = Config.ARENA_HEIGHT / 2;
        int step = 1;
        double amp = 15 + 50 * (this.logic.getMultiplier() - 1);
        for (int i = 0; i < Config.ARENA_WIDTH; i = i + step) {
            int y = (int) (yMid + amp * Math.sin((i / 20 + tick) / step));
            g.drawLine(i, y, i, y);
        }
    }

    /**
     * Draws "YOU WIN" and calls drawYesNo().
     */
    private void drawWon() {
        g.setFont(new Font("default", Font.BOLD, 40));
        g.setColor(animateColor());
        char[] youWin = ("Y O U   W I N ! ! !").toCharArray();
        g.drawChars(youWin, 0, youWin.length, 230, 150);
        drawYesNo();
    }

    /**
     * Calls drawWon() and drawYesNo().
     */
    private void drawWonHighscore() {
        drawWon();
        drawNewHighscore();
    }
}
