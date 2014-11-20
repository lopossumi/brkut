package com.milo.brkut.Main;

import com.milo.brkut.Logic.Player;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author milo
 */
public class UserInput implements KeyListener {

    //private Player player;
    private boolean[] keys;

    public UserInput(Player p) {
        //this.player = p;
        this.keys = new boolean[2];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            //player.accelerateX(-5);
            this.keys[0] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            //player.accelerateX(5);
            this.keys[1] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.keys[0] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.keys[1] = false;
        }
    }

    public boolean[] update() {
        return this.keys;
    }

}
