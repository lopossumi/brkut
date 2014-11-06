package com.milo.brkut.Main;

import com.milo.brkut.Logic.*;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
/**
 *
 * @author milo
 */
public class Panel extends JPanel {

    private Arena arena;

    public Panel(Arena arena) {
        this.arena = arena;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBackground(Color.BLACK);
        
        // Draw GameObjects
        for (Brick brick : arena.getBricks()) {
            drawGameObject(brick, g);
        }
        drawGameObject(arena.getPlayerOne(), g);
        drawGameObject(arena.getBall(), g);
        drawScore(g);

        getToolkit().sync();
    }

    public void drawGameObject(GameObject o, Graphics g) {
        g.setColor(o.getColor());
        g.fillRect(
                (int) (o.getX()-o.getWidth()/2),
                (int) (o.getY()-o.getHeight()/2),
                (int) o.getWidth(),
                (int) o.getHeight());
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.GREEN);
        char[] score = ("SCORE " + String.valueOf(arena.getScore())).toCharArray();
        g.drawChars(score, 0, score.length, 30,30);
    }
}
