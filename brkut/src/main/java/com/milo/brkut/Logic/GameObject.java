package com.milo.brkut.Logic;

import java.awt.Color;

/**
 * @author milo
 */
public abstract class GameObject {

    // Bounding box coordinates
    private double x;
    private double y;
    private double width;
    private double height;

    // Speed components
    private double vx;
    private double vy;
    Color color;

    public GameObject(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.vx = 0;
        this.vy = 0;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public double getVx() {
        return this.vx;
    }

    public double getVy() {
        return this.vy;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    /**
     * Moves the object (dx,dy) units.
     *
     * @param dx X coordinate difference
     * @param dy Y coordinate difference
     */
    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Moves the object according to its speed.
     */
    public void move() {
        this.x += vx;
        this.y += vy;
    }

    /**
     * Increases the object's speed on the X axis.
     *
     * @param amount
     */
    public void accelerateX(double amount) {
        this.vx += amount;
    }

    /**
     * Increases the object's speed on the Y axis.
     *
     * @param amount
     */
    public void accelerateY(double amount) {
        this.vy += amount;
    }

    /**
     * Increases the object's speed on both axes.
     *
     * @param amountX
     * @param amountY
     */
    public void accelerateXY(double amountX, double amountY) {
        this.accelerateX(amountX);
        this.accelerateY(amountY);
    }

    /**
     * Checks collision between two objects (this and the other).
     *
     * @param other the second object
     * @return -1 for vertical collision, 1 for horizontal, 0 for no collision.
     * For corner cases the default is vertical collision.
     */
    public int collision(GameObject other) {
        double xOverlapAmount = -Math.abs(this.getX() - other.getX()) + (this.getWidth() + other.getWidth()) / 2;
        double yOverlapAmount = -Math.abs(this.getY() - other.getY()) + (this.getHeight() + other.getHeight()) / 2;

        // No collision (overlap <= 0 on either axis)
        if (xOverlapAmount <= 0 || yOverlapAmount <= 0) {
            return 0;
        } else if (xOverlapAmount >= yOverlapAmount) {
            return -1;
        } else {
            return 1;
        }
    }

    public Color getColor() {
        return this.color;
    }
    
    public void damage(int amount){
    }
}
