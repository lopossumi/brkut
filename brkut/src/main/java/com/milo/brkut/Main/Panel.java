package com.milo.brkut.Main;

import com.milo.brkut.Logic.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
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

        // Draw bricks
        g.setColor(Color.GREEN);
        ArrayList<Brick> bricks = arena.getBricks();
        for (Brick brick : bricks) {
            g.drawRect(
                    (int) brick.getX(),
                    (int) brick.getY(),
                    (int) brick.getWidth(),
                    (int) brick.getHeight());
        }

        // Draw player
        g.setColor(Color.BLACK);
        Player playerOne = arena.getPlayerOne();
        g.drawRect(
                (int) playerOne.getX(),
                (int) playerOne.getY(),
                (int) playerOne.getWidth(),
                (int) playerOne.getHeight());
        
        // Draw ball
        g.setColor(Color.BLACK);
        Ball ball = arena.getBall();
        g.drawRect(
                (int) ball.getX(),
                (int) ball.getY(),
                (int) ball.getWidth(),
                (int) ball.getHeight());
        
        getToolkit().sync();
    }
}
