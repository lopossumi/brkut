package com.milo.brkut.Logic;

import java.util.ArrayList;

/**
 * @author milo
 */
public class Arena {

    private Player playerOne;
    private Ball ball;
    private ArrayList<Brick> bricks;
    private GameStatus status;

    public Arena() {
        this.status = GameStatus.RUNNING;
    }

    /**
     * Do operations for this step. - move stuff - check collisions and deaths
     */
    public void step() {
        this.updateStatus();
    }

    public void updateStatus() {
        if (bricks.isEmpty()) {
            this.status = GameStatus.WON;
        }
        if (playerOne.getLives() == 0) {
            this.status = GameStatus.GAMEOVER;
        }
    }

    public ArrayList<Brick> getBricks() {
        return this.bricks;
    }

    public Player getPlayerOne() {
        return this.playerOne;
    }

    public Ball getBall() {
        return this.ball;
    }
}
