package com.milo.brkut.Main;

import com.milo.brkut.Logic.KeypressEnum;
import com.milo.brkut.Logic.Player;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author milo
 */
public class UserInput implements KeyListener {

	//private Player player;
	private boolean[] keyPresses;

	public UserInput(Player p) {
		//this.player = p;
		this.keyPresses = new boolean[3];
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
		}
	}

	public boolean[] update() {
		boolean[] temp;
		temp = this.keyPresses.clone();
		this.keyPresses[KeypressEnum.SPACE.getValue()] = false;
		return temp;
	}

}
