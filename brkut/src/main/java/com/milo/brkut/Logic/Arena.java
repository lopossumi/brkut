package com.milo.brkut.Logic;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author milo
 */
public class Arena {

    private Player playerOne;
    private Ball ball;
    private HashSet<GameObject> bricks;
    private int score;
    private double multiplier;

    private boolean hit;
    private boolean launchAllowed;

    public Arena() {
        this.playerOne = new Player(100, 500, 100, 15);
        this.ball = new Ball(100, 480, 10, 10);
        this.bricks = new HashSet<>();
        addBricks();
        this.score = 0;
        this.hit = false;
        this.launchAllowed = true;
        this.multiplier = 1;
    }

    /**
     * Do operations for this step. - move stuff - check collisions and deaths
     *
     * @param input Keyboard inputs
     */
    public void step(boolean[] input) {

        moveBall(input);
        movePlayerOne(input);

        // Check collisions with all the bricks, give points and bounce
        ArrayList<GameObject> collisions = Collision.getColliders(this.ball, this.bricks);
        if (!collisions.isEmpty()) {
            handleCollisionsWithBricks(collisions);
        }

        handleCollisionWithPlayerOne();

        if (ball.getX() < 0 || ball.getX() > 800) {
            ball.bounceHorizontal();
        }
        if (ball.getY() < 0) {
            ball.bounceVertical();
        }
        if (ball.getY() > 600) {
            playerOne.kill();
        }
    }

    /**
     * Selects a correct game status from a list of possible values.
     *
     * @return
     */
    public GameStatusEnum getStatus() {
        if (launchAllowed) {
            return GameStatusEnum.START;
        } else if (this.hit) {
            this.hit = false;
            return GameStatusEnum.HIT;
        } else if (bricks.isEmpty()) {
            return GameStatusEnum.WON;
        } else if (playerOne.getLives() == 0) {
            return GameStatusEnum.GAMEOVER;
        } else if (!playerOne.isAlive()) {
            return GameStatusEnum.DIED;
        } else {
            return GameStatusEnum.RUNNING;
        }
    }

    /**
     * Moves player one according to input. Checks collisions between walls.
     *
     * @param input
     */
    private void movePlayerOne(boolean[] input) {
        if (input[KeypressEnum.LEFT.getValue()]) {
            playerOne.accelerateX(-1);
        }
        if (input[KeypressEnum.RIGHT.getValue()]) {
            playerOne.accelerateX(1);
        }
        playerOne.move();
        playerOne.decelerate();

        //No wall clipping allowed: stop paddle, move back to playing field
        if (playerOne.getX() - playerOne.getWidth() / 2 < 0) {
            playerOne.stop();
            playerOne.moveTo(0 + playerOne.getWidth() / 2, playerOne.getY());
        } else if (playerOne.getX() + playerOne.getWidth() / 2 > 800) {
            playerOne.stop();
            playerOne.moveTo(800 - playerOne.getWidth() / 2, playerOne.getY());
        }
    }

    /**
     * Move the ball at its internal speed. If launch is allowed, check if space
     * bar is pressed and launch ball.
     *
     * @param input
     */
    private void moveBall(boolean[] input) {
        if (input[KeypressEnum.SPACE.getValue()]
                && this.launchAllowed == true
                && Math.abs(this.ball.getX() - this.playerOne.getX()) < this.playerOne.getWidth() / 2) {
            ball.accelerateXY((this.ball.getX() - this.playerOne.getX()) / 15, -5);
            this.launchAllowed = false;
        }
        ball.move();
    }

    /**
     * Adds points and damages bricks, bounces ball in a correct direction.
     *
     * @param collisions List of bricks in collision state with ball
     */
    private void handleCollisionsWithBricks(ArrayList<GameObject> collisions) {
        this.hit = true;
        this.score += multiplier * 100;
        this.multiplier += 0.1;
        GameObject brick = collisions.get(0);

        switch (Collision.collision(this.ball, brick)) {
            case VERTICAL:
                ball.bounceVertical();
                break;
            case HORIZONTAL:
                ball.bounceHorizontal();
                break;
            default:
                break;
        }
        Collision.clear(this.ball, brick);
        brick.damage(1);
        if (!brick.isAlive()) {
            this.bricks.remove(brick);
        }
    }

    /**
     * Checks for collision between player one and the ball. Bounces the ball if
     * necessary, and resets the bonus multiplier.
     */
    void handleCollisionWithPlayerOne() {
        switch (Collision.collision(this.ball, this.playerOne)) {
            case VERTICAL:
                ball.bounceVertical();
                ball.accelerateX((this.ball.getX() - this.playerOne.getX()) / 30);
                this.multiplier = 1;
                Collision.clear(ball, this.playerOne);
                break;
            case HORIZONTAL:
                ball.bounceHorizontal();
                this.multiplier = 1;
                Collision.clear(ball, this.playerOne);
                break;
            default:
                break;
        }
    }

    /**
     * Reset playing arena after death. Set paddle and ball position, allow
     * launch.
     */
    public void reset() {
        this.playerOne.respawn();
        this.ball.stop();
        this.ball.moveTo(100, 480);
        this.launchAllowed = true;
    }

    // TODO: This is for testing and should be rewritten to use some sort of 
    // level generation thingie.
    private void addBricks() {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 12; x++) {
                Brick brick = new Brick(80 + x * 60, 70 + y * 50, 50, 30);
                brick.setColor(new Color(20 * x, 250 - 20 * y, 0));
                this.bricks.add(brick);
            }
        }
    }

    // Setters and getters
    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public HashSet<GameObject> getBricks() {
        return this.bricks;
    }

    public Player getPlayerOne() {
        return this.playerOne;
    }

    public Ball getBall() {
        return this.ball;
    }

    public int getScore() {
        return this.score;
    }
}
