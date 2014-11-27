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

    public Panel(Arena arena) {
        this.arena = arena;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBackground(Color.BLACK);
        g.setFont(new Font("default", Font.BOLD, 16));
        
        // Draw GameObjects
        for (GameObject brick : arena.getBricks()) {
            drawGameObject(brick, g);
        }
        drawGameObject(arena.getPlayerOne(), g);
        drawGameObject(arena.getBall(), g);
        drawScore(g);
        drawLives(g);

        getToolkit().sync();
    }

    public void drawGameObject(GameObject o, Graphics g) {
        if(o.getWidth()>1){
        g.setColor(o.getColor());
        g.fill3DRect(
                (int) (o.getX()-o.getWidth()/2),
                (int) (o.getY()-o.getHeight()/2),
                (int) o.getWidth(),
                (int) o.getHeight(),
		true);
        }
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.GREEN);
        char[] score = ("SCORE  " + String.valueOf(arena.getScore())).toCharArray();
        g.drawChars(score, 0, score.length, 30,30);
    }
    
        private void drawLives(Graphics g) {
        g.setColor(Color.GREEN);
        char[] lives = ("LIVES  " + String.valueOf(arena.getPlayerOne().getLives())).toCharArray();
        g.drawChars(lives, 0, lives.length,300,30);
    }
}
