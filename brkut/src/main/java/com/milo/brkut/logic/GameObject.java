package com.milo.brkut.logic;

import java.awt.Color;

/**
 * An abstract GameObject (implemented as Ball, Brick or Player).
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
    private double maxSpeed;

    Color color;

    public GameObject(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.vx = 0;
        this.vy = 0;
        this.maxSpeed = 0;
    }

    /**
     * Sets the speed to zero on both axes.
     */
    public void stop() {
        this.vy = 0;
        this.vx = 0;
    }

    public void moveTo(double x, double y) {
        this.x = x;
        this.y = y;
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
        if (this.vx > this.maxSpeed) {
            this.vx = this.maxSpeed;
        }
        if (this.vx < -this.maxSpeed) {
            this.vx = -this.maxSpeed;
        }
    }

    /**
     * Increases the object's speed on the Y axis.
     *
     * @param amount
     */
    public void accelerateY(double amount) {
        this.vy += amount;
        if (this.vy > this.maxSpeed) {
            this.vy = this.maxSpeed;
        }
        if (this.vy < -this.maxSpeed) {
            this.vy = -this.maxSpeed;
        }
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

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void damage(int amount) {
    }

    boolean isAlive() {
        return true;
    }

    public void decelerate() {
        this.vx = this.vx * 0.9;
    }

    // Getters and setters
    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getVx() {
        return this.vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return this.vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
