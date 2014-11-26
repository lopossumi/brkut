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
	private boolean hit;

	public Arena() {
		this.playerOne = new Player(100, 500, 100, 10);
		this.ball = new Ball(100, 480, 10, 10);
		//this.ball.accelerateXY(0.7, -5);
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
					y * 50 + 50,
					50,
					30));
			}
		}
	}

	/**
	 * Do operations for this step. - move stuff - check collisions and
	 * deaths
	 *
	 * @param input Keyboard inputs
	 */
	public void step(boolean[] input) {

		moveBall(input);
		movePlayerOne(input);
		ball.move();

		// Check collisions with all the bricks, give points and bounce
		ArrayList<GameObject> collisions = Collision.getColliders(this.ball, this.bricks);
		if (!collisions.isEmpty()) {
			this.hit = true;
			this.score += 100;
			GameObject brick = collisions.get(0);

			switch (Collision.collision(this.ball, brick)) {
			case VERTICAL:
				ball.bounceVertical();
				//ball.accelerateX((this.ball.getX()-this.playerOne.getX())/15);
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

		switch (Collision.collision(this.ball, this.playerOne)) {
			case VERTICAL:
				ball.bounceVertical();
				ball.accelerateX((this.ball.getX()-this.playerOne.getX())/15);
				break;
			case HORIZONTAL:
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

	public GameStatus getStatus() {
		if (this.hit) {
			this.hit = false;
			return GameStatus.HIT;
		} else if (bricks.isEmpty()) {
			return GameStatus.WON;
		} else if (playerOne.getLives() == 0) {
			return GameStatus.GAMEOVER;
		} else if (!playerOne.isAlive()) {
			return GameStatus.DIED;
		} else {
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

	public int getScore() {
		return this.score;
	}

	private void movePlayerOne(boolean[] input) {
		if (input[KeypressEnum.LEFT.getValue()]) {
			playerOne.accelerateX(-1);
		}
		if (input[KeypressEnum.RIGHT.getValue()]) {
			playerOne.accelerateX(1);
		}
		playerOne.move();
		playerOne.decelerate();
	}
	
	private void moveBall(boolean[] input){
		if(input[KeypressEnum.SPACE.getValue()]){
			ball.accelerateXY(0.6,-5);
		}
	}
}
