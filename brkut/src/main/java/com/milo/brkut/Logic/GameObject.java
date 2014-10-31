package com.milo.brkut.Logic;

/**
 * @author milo
 */
public abstract class GameObject {

        // Bounding box coordinates
	private double left;
	private double right;
	private double top;
	private double bottom;

	// Speed components
	private double vx;
	private double vy;

	public GameObject(double x, double y, double width, double height) {
		this.left = x - width / 2;
		this.right = x + width / 2;
		this.top = y - height / 2;
		this.bottom = y + height / 2;
		this.vx = 0;
		this.vy = 0;
	}

	public void move(double x, double y) {
		this.left += x;
		this.right += x;
		this.top += y;
		this.bottom += y;
	}

	public void move() {
		this.left += vx;
		this.right += vx;
		this.top += vy;
		this.bottom += vy;
	}

	public void accelerateX(double amount) {
		this.vx += amount;
	}

	public void accelerateY(double amount) {
		this.vy += amount;
	}

	public void accelerateXY(double amountX, double amountY) {
		this.accelerateX(amountX);
		this.accelerateY(amountY);
	}
}
