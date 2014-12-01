package com.milo.brkut.Logic;

import com.milo.brkut.Main.Config;
import java.util.HashSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author milo
 */
public class ArenaTest {

    public ArenaTest() {
    }

    /**
     * Test of step method, of class Arena.
     */
    @Test
    public void testStep() {
        System.out.println("step");
        Arena instance = new Arena(0);

        // After 1000 steps, we should have hit something. Anything.
        instance.getBall().setVy(-5);
        for (int i = 0; i < 1000; i++) {
            instance.step(new boolean[3]);
        }
        assertTrue(instance.getScore() > 0);

        //Bounces from the sides
        instance.reset();
        instance.getBall().setVx(-10);
        for (int i = 0; i < 20; i++) {
            instance.step(new boolean[3]);
        }
        assertEquals(instance.getBall().getVx(), 10, 0.0);
        for (int i = 0; i < 80; i++) {
            instance.step(new boolean[3]);
        }
        assertEquals(instance.getBall().getVx(), -10, 0.0);

    }

    /**
     * Test of getStatus method, of class Arena.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        Arena instance = new Arena(0);
        assertEquals(GameStatusEnum.START, instance.getStatus());

        instance.setLaunchAllowed(false);
        assertEquals(GameStatusEnum.RUNNING, instance.getStatus());

        instance.getPlayerOne().kill();
        assertEquals(GameStatusEnum.DIED, instance.getStatus());

        instance.reset();
        assertEquals(GameStatusEnum.START, instance.getStatus());

        instance.setLaunchAllowed(false);
        instance.setHit(true);
        assertEquals(GameStatusEnum.HIT, instance.getStatus());

        HashSet brixx = new HashSet<GameObject>();
        instance.setBricks(brixx);
        assertEquals(GameStatusEnum.WON, instance.getStatus());
        brixx.add(new Brick(1, 1, 1, 1));

        instance.getPlayerOne().kill();
        instance.getPlayerOne().kill();
        assertEquals(GameStatusEnum.GAMEOVER, instance.getStatus());

        instance.setScore(100);
        assertEquals(GameStatusEnum.HIGHSCORE, instance.getStatus());
    }

    /**
     * Test of getBricks method, of class Arena.
     */
    @Test
    public void testGetBricks() {
        System.out.println("getBricks");
        Arena instance = new Arena(0);
        int expResult = 60;
        HashSet<GameObject> result = instance.getBricks();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getPlayerOne method, of class Arena.
     */
    @Test
    public void testGetPlayerOne() {
        System.out.println("getPlayerOne");
        Arena instance = new Arena(0);
        int expResult = 3;
        Player result = instance.getPlayerOne();
        assertEquals(expResult, result.getLives());
    }

    /**
     * Test of getBall method, of class Arena.
     */
    @Test
    public void testGetBall() {
        System.out.println("getBall");
        Arena instance = new Arena(0);
        int expResult = 0;
        Ball result = instance.getBall();
        assertEquals(expResult, result.getVy(), 0.0);
    }

    /**
     * Test of getBall method, of class Arena.
     */
    @Test
    public void testHandleCollisionWithPlayerOne() {
        Arena instance = new Arena(0);
        Ball ball = instance.getBall();
        Player pOne = instance.getPlayerOne();

        //Vertical
        instance.setMultiplier(2);
        ball.moveTo(60, 490);
        ball.setVx(0);
        ball.setVy(5);
        pOne.moveTo(100, 500);
        instance.handleCollisionWithPlayerOne();
        assertEquals(1.0, instance.getMultiplier(), 0.0);
        assertEquals(-5, ball.getVy(), 0.0);

        // Check if ball Y coordinate and X velocity have changed
        // (Collision.clear & bounce directional function)
        assertTrue(ball.getVx() < 0);
        assertTrue(ball.getY() < 490);

        //Horizontal
        instance.setMultiplier(2);
        ball.moveTo(46, 500);
        ball.setVx(5);
        ball.setVy(0);
        instance.handleCollisionWithPlayerOne();
        assertEquals(1.0, instance.getMultiplier(), 0.0);
        assertEquals(-5, ball.getVx(), 0.0);
        assertEquals(1.0, instance.getMultiplier(), 0.0);
        assertEquals(-5, ball.getVx(), 0.0);
        assertTrue(ball.getX() < 46);
    }

    @Test
    public void testReset() {
        Arena instance = new Arena(0);
        Ball ball = instance.getBall();
        Player pOne = instance.getPlayerOne();

        //Set some values for speeds and positions
        pOne.setX(400);
        pOne.setY(420);
        pOne.accelerateXY(10, 10);
        ball.setX(123);
        ball.setY(456);
        ball.accelerateXY(10, 10);

        pOne.kill();
        assertTrue(!pOne.isAlive());

        instance.reset();
        assertTrue(pOne.isAlive());
        assertEquals(Config.PLAYER_START_X, pOne.getX(), 0.0);
        assertEquals(Config.BALL_START_X, ball.getX(), 0.0);
    }
}
