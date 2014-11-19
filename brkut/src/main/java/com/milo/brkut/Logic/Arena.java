package com.milo.brkut.Logic;

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

    public Arena() {
        this.playerOne = new Player(100, 500, 100, 10);
        this.ball = new Ball(130, 530, 10, 10);
        this.ball.accelerateXY(0.31, -1);
        this.bricks = new HashSet<>();
        addBricks();
        this.score = 0;
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
     */
    public void step() {
        playerOne.move();
        ball.move();
        playerOne.decelerate();

        ArrayList<GameObject> collisions = Collision.checkCollisions(this.ball, this.bricks);
        
        while (!collisions.isEmpty()) {
            this.score += 100;
            GameObject collider = collisions.get(0);
            if (this.ball.collision(collider)==-1) {
                this.ball.bounceVertical();
            } else 
                this.ball.bounceHorizontal();
            collider.damage(1);
            if(!collider.isAlive()) {
                this.bricks.remove(collider);
            }
            collisions.remove(collider);
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
    
    public GameStatus getStatus(){
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

    public HashSet<GameObject> getBricks() {
        return this.bricks;
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