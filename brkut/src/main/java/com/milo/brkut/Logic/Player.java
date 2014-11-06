package com.milo.brkut.Logic;

import java.awt.Color;

/**
 * @author milo
 */
public class Player extends GameObject {

	private int lives;

	public Player(double x, double y, double width, double height) {
		super(x, y, width, height);
		this.lives = 3;
                this.color = Color.WHITE;
	}

	public int getLives() {
		return this.lives;
	}

	public void addLife() {
		this.lives++;
	}

}
