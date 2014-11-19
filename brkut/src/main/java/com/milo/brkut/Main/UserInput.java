package com.milo.brkut.Main;

import com.milo.brkut.Logic.Player;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author milo
 */
public class UserInput implements KeyListener {

    private Player player;

    public UserInput(Player p) {
        this.player = p;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.accelerateX(-5);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.accelerateX(5);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
