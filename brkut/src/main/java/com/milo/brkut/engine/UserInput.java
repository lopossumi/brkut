package com.milo.brkut.engine;

import com.milo.brkut.logic.Player;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author milo
 */
public class UserInput implements KeyListener {

    private final boolean[] keyPresses;

    public UserInput(Player p) {
        this.keyPresses = new boolean[5];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.keyPresses[KeypressEnum.LEFT.getValue()] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.keyPresses[KeypressEnum.RIGHT.getValue()] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.keyPresses[KeypressEnum.SPACE.getValue()] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_Y) {
            this.keyPresses[KeypressEnum.Y.getValue()] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_N) {
            this.keyPresses[KeypressEnum.N.getValue()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.keyPresses[KeypressEnum.LEFT.getValue()] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.keyPresses[KeypressEnum.RIGHT.getValue()] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.keyPresses[KeypressEnum.SPACE.getValue()] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_Y) {
            this.keyPresses[KeypressEnum.Y.getValue()] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_N) {
            this.keyPresses[KeypressEnum.N.getValue()] = false;
        }
    }

    public boolean[] update() {
        boolean[] temp;
        temp = this.keyPresses.clone();
        this.keyPresses[KeypressEnum.SPACE.getValue()] = false;
        this.keyPresses[KeypressEnum.Y.getValue()] = false;
        this.keyPresses[KeypressEnum.N.getValue()] = false;
        return temp;
    }

}
