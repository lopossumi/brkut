package com.milo.brkut.Logic;

/**
 * @author milo
 */
public class Player extends GameObject {

	private int lives;

	public Player(double x, double y, double width, double height) {
		super(x, y, width, height);
		this.lives = 3;
	}

	public int getLives() {
		return this.lives;
	}

	public void addLife() {
		this.lives++;
	}

}
