package com.milo.brkut.Main;

import com.milo.brkut.Logic.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
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
        // Draw bricks
        g.setColor(Color.GREEN);
        HashSet<Brick> bricks = arena.getBricks();
        for (Brick brick : bricks) {
            drawGameObject(brick, g);
        }

        // Draw player
        g.setColor(Color.WHITE);
        Player playerOne = arena.getPlayerOne();
        drawGameObject(playerOne, g);

        // Draw ball
        g.setColor(Color.WHITE);
        Ball ball = arena.getBall();
        drawGameObject(ball, g);

        getToolkit().sync();
    }

    public void drawGameObject(GameObject o, Graphics g) {
        g.setColor(o.getColor());
        g.fillRect(
                (int) o.getX(),
                (int) o.getY(),
                (int) o.getWidth(),
                (int) o.getHeight());
    }
}
