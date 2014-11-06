package com.milo.brkut.Logic;

import java.awt.Color;

/**
 * @author milo
 */
public class Brick extends GameObject {

	private int hitpoints;

	public Brick(double x, double y, double width, double height) {
		super(x, y, width, height);
		this.hitpoints = 2;
                this.color = Color.GREEN;
	}

	public void damage(int amount) {
		this.hitpoints -= amount;
                this.color = Color.RED;
	}

	public int hp() {
		return this.hitpoints;
	}
}
