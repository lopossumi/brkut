package com.milo.brkut.Logic;

import java.util.HashSet;

/**
 * @author milo
 */
public class Arena {

    private Player playerOne;
    private Ball ball;
    private HashSet<Brick> bricks;
    private int score;
    private boolean hit;

    public Arena() {
        this.playerOne = new Player(100, 500, 100, 10);
        this.ball = new Ball(130, 530, 10, 10);
        this.ball.accelerateXY(0.31, -1);
        this.bricks = new HashSet<>();
        addBricks();
        this.score = 0;
        this.hit = false;
    }

    // TODO: This is for testing and should be rewritten to use some sort of 
    // level generation thingie.
    private void addBricks() {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 12; x++) {
                this.bricks.add(new Brick(
                        x * 60 + 80,
                        y * 25 + 50,
                        55,
                        20));
            }
        }
    }

    /**
     * Do operations for this step. - move stuff - check collisions and deaths
     * @return 
     */
    public GameStatus step() {
        ball.move();

        // If a brick is destroyed, it is stored here.
        Brick destroyed;
        destroyed = checkCollisionsWithBricks();
        if (destroyed != null) {
            bricks.remove(destroyed);
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
        return getStatus();
    }
    
    public GameStatus getStatus(){
        if (this.hit){
            this.hit = false;
            return GameStatus.HIT;
        }
        if (bricks.isEmpty()) {
            return GameStatus.WON;
        }
        else if (playerOne.getLives() == 0) {
            return GameStatus.GAMEOVER;
        } 
        else if (!playerOne.isAlive()) {
            return GameStatus.DIED;
        }
        else {
            return GameStatus.RUNNING;
        }
    }

    public HashSet<Brick> getBricks() {
        return this.bricks;
    }

    private Brick checkCollisionsWithBricks() {
        Brick destroyed = null;
        for (Brick brick : this.bricks) {
            int i = ball.collision(brick);
            switch (i) {
                case -1: case 1:
                    if (i==-1) 
                        ball.bounceVertical();
                    else 
                        ball.bounceHorizontal();
                    
                    brick.damage(1);
                    if (!brick.isAlive()) {
                        destroyed = brick;
                    }
                    this.score += 100;
                    this.hit = true;

                    break;
               
                default:
                    break;
            }
        }
        return destroyed;
    }

    public Player getPlayerOne() {
        return this.playerOne;
    }

    public Ball getBall() {
        return this.ball;
    }
    
    public int getScore(){
        return this.score;
    }
}
