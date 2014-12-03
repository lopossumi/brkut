package com.milo.brkut.Logic;

import com.milo.brkut.Engine.KeypressEnum;
import com.milo.brkut.Main.Config;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author milo
 */
public class Logic {

    private final Player playerOne;
    private final Ball ball;
    private HashSet<GameObject> bricks;
    private int score;
    private double multiplier;
    private int highScore;

    private boolean hit;
    private boolean launchAllowed;

    public Logic(int highScore) {
        this.playerOne = new Player(
                Config.PLAYER_START_X,
                Config.PLAYER_START_Y,
                Config.PLAYER_WIDTH,
                Config.PLAYER_HEIGHT);

        this.ball = new Ball(
                Config.BALL_START_X,
                Config.BALL_START_Y,
                Config.BALL_WIDTH,
                Config.BALL_HEIGHT);

        this.bricks = new HashSet<>();
        addBricks(5, 12);

        this.score = 0;
        this.highScore = highScore;
        this.multiplier = 1.0;

        this.hit = false;
        this.launchAllowed = true;
    }

    /**
     * Do operations for this step. - move stuff around - check collisions and
     * deaths
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

        if (ball.getX() < 0 || ball.getX() > Config.ARENA_WIDTH) {
            ball.bounceHorizontal();
        }
        if (ball.getY() < 0) {
            ball.bounceVertical();
        }
        if (ball.getY() > Config.ARENA_HEIGHT) {
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
        } else if (playerOne.getLives() == 0
                && this.score > this.highScore) {
            return GameStatusEnum.HIGHSCORE;
        } else if (playerOne.getLives() == 0) {
            return GameStatusEnum.GAMEOVER;
        } else if (!playerOne.isAlive()) {
            return GameStatusEnum.DIED;
        } else {
            return GameStatusEnum.RUNNING;
        }
    }

    /**
     * Moves player one according to input and checks collisions between walls.
     *
     * @param input Keyboard inputs
     */
    public void movePlayerOne(boolean[] input) {
        if (input[KeypressEnum.LEFT.getValue()]) {
            playerOne.accelerateX(-Config.PLAYER_ACCELERATION);
        }
        if (input[KeypressEnum.RIGHT.getValue()]) {
            playerOne.accelerateX(Config.PLAYER_ACCELERATION);
        }
        playerOne.move();
        playerOne.decelerate();

        //No wall clipping allowed: stop paddle, move back to playing field
        if (playerOne.getX() - playerOne.getWidth() / 2 < 0) {
            playerOne.stop();
            playerOne.moveTo(0 + playerOne.getWidth() / 2, playerOne.getY());
        } else if (playerOne.getX() + playerOne.getWidth() / 2 > Config.ARENA_WIDTH) {
            playerOne.stop();
            playerOne.moveTo(Config.ARENA_WIDTH - playerOne.getWidth() / 2, playerOne.getY());
        }
    }

    public boolean isLaunchAllowed() {
        return launchAllowed;
    }

    /**
     * Move the ball at its internal speed; if launch is allowed, check if space
     * bar is pressed and launch ball.
     *
     * @param input Keyboard inputs
     */
    public void moveBall(boolean[] input) {
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
                ball.accelerateX((this.ball.getX() - this.playerOne.getX()) / 20);
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
        this.playerOne.respawn(Config.PLAYER_START_X, Config.PLAYER_START_Y);
        this.ball.stop();
        this.ball.moveTo(Config.BALL_START_X, Config.BALL_START_Y);
        this.launchAllowed = true;
    }

    /**
     * Creates bricks for an arena.
     *
     * @param rows How many rows of bricks
     * @param columns How many columns of bricks
     */
    private void addBricks(int rows, int columns) {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                Brick brick = new Brick(
                        x * Config.ARENA_WIDTH / columns + 30,
                        y * (Config.ARENA_HEIGHT / 3) / rows + 60,
                        50,
                        30);
                brick.setColor(new Color(20 * x, 250 - 20 * y, 0));
                this.bricks.add(brick);
            }
        }
    }

    // Setters and getters
    public double getMultiplier() {
        return multiplier;
    }

    /**
     * Sets the bonus multiplier to a given value.
     *
     * @param multiplier
     */
    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public HashSet<GameObject> getBricks() {
        return this.bricks;
    }

    public void setBricks(HashSet<GameObject> bricks) {
        this.bricks = bricks;
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

    public void setScore(int score) {
        this.score = score;
    }

    public int getHighscore() {
        return this.highScore;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public void setLaunchAllowed(boolean launchAllowed) {
        this.launchAllowed = launchAllowed;
    }

}
