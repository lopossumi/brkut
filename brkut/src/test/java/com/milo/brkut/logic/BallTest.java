package com.milo.brkut.logic;

import com.milo.brkut.logic.Ball;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author milo
 */
public class BallTest {

    public BallTest() {
    }

    /**
     * Test of bounceHorizontal method, of class Ball.
     */
    @Test
    public void testBounceHorizontal() {
        System.out.println("bounceHorizontal");
        Ball instance = new Ball(10, 10, 10, 10);
        instance.accelerateXY(10, 10);
        instance.bounceHorizontal();
        assertEquals(instance.getVx(), -10.0, 0.0);
    }

    /**
     * Test of bounceVertical method, of class Ball.
     */
    @Test
    public void testBounceVertical() {
        System.out.println("bounceVertical");
        Ball instance = new Ball(10, 10, 10, 10);
        instance.accelerateXY(10, 10);
        instance.bounceVertical();
        assertEquals(instance.getVx(), 10.0, -10.0);
    }

}
