package com.milo.brkut.Main;

import com.milo.brkut.Logic.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author milo
 */
public class Panel extends JPanel {

    private Arena arena;
    private int tick;

    public Panel(Arena arena) {
        this.arena = arena;
        this.tick = 0;
    }

    private void tick() {
        if (this.tick > 2000) {
            this.tick = 0;
        } else {
            this.tick++;
        }
    }

    @Override
    public void paint(Graphics g) {
        tick();
        super.paint(g);
        this.setBackground(Color.BLACK);

        if (arena.getStatus() == GameStatusEnum.START) {
            drawStart(g);
        }
        if (arena.getStatus() == GameStatusEnum.GAMEOVER) {
            drawGameOver(g);
        }
        drawScore(g);
        drawLives(g);
        drawMultiplier(g);
        drawHighscore(g);

        // Draw GameObjects
        for (GameObject brick : arena.getBricks()) {
            drawGameObject(brick, g);
        }
        drawGameObject(arena.getPlayerOne(), g);
        drawGameObject(arena.getBall(), g);
        getToolkit().sync();
    }

    public void drawGameObject(GameObject o, Graphics g) {
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

    private void drawScore(Graphics g) {
        g.setFont(new Font("default", Font.BOLD, 16));
        g.setColor(Color.GREEN);
        char[] score = ("SCORE  " + String.valueOf(arena.getScore())).toCharArray();
        g.drawChars(score, 0, score.length, 300, 30);
    }

    private void drawLives(Graphics g) {
        g.setFont(new Font("default", Font.BOLD, 16));
        g.setColor(Color.GREEN);
        char[] lives = ("LIVES  " + String.valueOf(arena.getPlayerOne().getLives())).toCharArray();
        g.drawChars(lives, 0, lives.length, 30, 30);
    }

    private void drawMultiplier(Graphics g) {
        g.setFont(new Font("default", Font.BOLD, 16));
        g.setColor(Color.GREEN);
        char[] multiplier = ("BONUS  " + String.format("%.2g%n", arena.getMultiplier()) + "x").toCharArray();
        g.drawChars(multiplier, 0, multiplier.length, 150, 30);
    }

    private void drawHighscore(Graphics g) {
        g.setFont(new Font("default", Font.BOLD, 16));
        g.setColor(Color.GREEN);
        char[] highscore = ("HIGH SCORE  " + String.valueOf(arena.getHighscore())).toCharArray();
        g.drawChars(highscore, 0, highscore.length, 500, 30);
    }

    private void drawStart(Graphics g) {
        g.setFont(new Font("default", Font.BOLD, 40));
        g.setColor(Color.GREEN);
        char[] start = ("PLAYER ONE GET READY...").toCharArray();
        g.drawChars(start, 0, start.length, 130, 350);
        g.setFont(new Font("default", Font.BOLD, 30));

        // Animate color
        g.setColor(new Color(
                (int) (127 + 127 * Math.sin(this.tick / 17)),
                (int) (127 + 127 * Math.sin(this.tick / 11)),
                (int) (127 + 127 * Math.sin(this.tick / 19))));
        start = ("(PRESS SPACE TO LAUNCH)").toCharArray();
        g.drawChars(start, 0, start.length, 180, 400);
    }

    private void drawGameOver(Graphics g) {
        g.setFont(new Font("default", Font.BOLD, 40));
        g.setColor(Color.GREEN);
        char[] gameover = ("G A M E   O V E R").toCharArray();
        g.drawChars(gameover, 0, gameover.length, 230, 350);
    }
}
