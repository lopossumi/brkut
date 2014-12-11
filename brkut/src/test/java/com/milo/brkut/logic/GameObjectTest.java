package com.milo.brkut.logic;

import com.milo.brkut.logic.Ball;
import com.milo.brkut.logic.Brick;
import com.milo.brkut.logic.GameObject;
import com.milo.brkut.logic.Collision.bounce;
import org.junit.Test;
import static org.junit.Assert.*;
import static com.milo.brkut.logic.Collision.collision;

/**
 *
 * @author milo
 */
public class GameObjectTest {

    public GameObjectTest() {
    }

    /**
     * Testing collision between the ball and a brick.
     */
    @Test
    public void testCollisionBallBrick() {
        System.out.println("Brick and ball collision");
        GameObject ball = new Ball(0, 0, 10, 10);
        GameObject brick = new Brick(100, 100, 100, 10);
        //no collision
        assertEquals(bounce.NONE, collision(ball, brick));

        //vertical collision from bottom
        ball.move(60, 91);
        assertEquals(bounce.VERTICAL, collision(ball, brick));
    }

    @Test
    public void testAccelerations() {
        GameObject ball = new Ball(0, 0, 10, 10);
        assertTrue(ball.getVx() == 0);
        assertTrue(ball.getVy() == 0);
        double maxS = ball.getMaxSpeed();

        ball.accelerateX(1);
        assertTrue(ball.getVx() == 1);
        ball.accelerateX(100);
        assertTrue(ball.getVx() == maxS);

        ball.accelerateY(1);
        assertTrue(ball.getVy() == 1);
        ball.accelerateY(100);
        assertTrue(ball.getVy() == maxS);

        ball.decelerate();
        assertTrue(ball.getVx() < maxS);

        ball.stop();
        assertTrue(ball.getVx() == 0);
        assertTrue(ball.getVy() == 0);

        ball.accelerateY(-100);
        assertTrue(ball.getVy() == -maxS);
        ball.accelerateX(-100);
        assertTrue(ball.getVx() == -maxS);
    }

    /**
     * Test of move method, of class GameObject.
     */
    @Test
    public void testMove() {
        System.out.println("Move");
        GameObject instance = new Ball(0, 0, 10, 10);
        instance.move(10, 10);
        assertEquals(10.0, instance.getX(), 0.0);
        assertEquals(10.0, instance.getY(), 0.0);

        instance.setVx(-5);
        instance.setVy(-4);
        instance.move();
        assertEquals(5.0, instance.getX(), 0.0);
        assertEquals(6.0, instance.getY(), 0.0);

    }

}
