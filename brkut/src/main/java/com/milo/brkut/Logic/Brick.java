package com.milo.brkut.Logic;

/**
 * @author lopossumi
 */
public class Brick extends GameObject {

	private int hitpoints;

	public Brick(double x, double y, double width, double height) {
		super(x, y, width, height);
		this.hitpoints = 2;
	}

	public void damage(int amount) {
		this.hitpoints -= amount;
	}

	public int hp() {
		return this.hitpoints;
	}
}
