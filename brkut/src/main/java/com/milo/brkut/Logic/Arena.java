package com.milo.brkut.Logic;

import java.util.HashSet;

/**
 * @author milo
 */
public class Arena {

    private Player playerOne;
    private Ball ball;
    private HashSet<Brick> bricks;
    private GameStatus status;

    public Arena() {
        this.playerOne = new Player(100, 500, 100, 10);
        this.ball = new Ball(130, 530, 10, 10);
        this.ball.accelerateXY(0.25, 1);
        this.bricks = new HashSet<>();
        addBricks();
        this.status = GameStatus.RUNNING;
    }

    private void addBricks() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 10; x++) {
                this.bricks.add(new Brick(
                        x * 60 + 50,
                        y * 50 + 50,
                        50,
                        10));
            }
        }
    }

    /**
     * Do operations for this step. - move stuff - check collisions and deaths
     */
    public void step() {
        this.updateStatus();
        ball.move();

        Brick temp = null;
        // Check against all bricks, no optimization here yet.
        for (Brick brick : bricks) {
            switch (ball.collision(brick)) {
                case -1:
                    brick.damage(1);
                    if (brick.hp() == 0) {
                        temp = brick;
                    }
                    ball.bounceVertical();
                    break;
                case 1:
                    ball.bounceHorizontal();
                    break;
                default:
                    break;
            }
        }
        if (temp != null){
            bricks.remove(temp);
        }

        switch (ball.collision(playerOne)) {
            case -1:
                ball.bounceVertical();
                break;
            case 1:
                ball.bounceHorizontal();
                break;
            default:
                break;
        }

        if (ball.getX() < 0 || ball.getX() > 800) {
            ball.bounceHorizontal();
        }
        if (ball.getY() < 0 || ball.getY() > 600) {
            ball.bounceVertical();
        }
    }

    public void updateStatus() {
        if (bricks.isEmpty()) {
            this.status = GameStatus.WON;
        }
        if (playerOne.getLives() == 0) {
            this.status = GameStatus.GAMEOVER;
        } else {
            this.status = GameStatus.RUNNING;
        }
    }

    public HashSet<Brick> getBricks() {
        return this.bricks;
    }

    public Player getPlayerOne() {
        return this.playerOne;
    }

    public Ball getBall() {
        return this.ball;
    }
}
