package com.milo.brkut.Logic;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author milo
 */
public class CollisionTest {

    public CollisionTest() {
    }

    @Test
    public void testClear() {
        System.out.println("TestClear");
        Brick brick = new Brick(100,100,100,100);
        Ball ball = new Ball(70,100,10,10);
        Collision.clear(ball, brick);
        //Ball should move from initial X position
        assertEquals(45, ball.getX(), 0.0);
    }
    
    @Test
    public void testOverlap(){
        System.out.println("XOverlap");
        Brick brick = new Brick(100,100,100,100);
        Ball ball = new Ball(70,100,10,10);
        //Should have 25px X overlap and 55px Y overlap
        assertEquals(25, Collision.xOverlap(brick, ball), 0.0);
        assertEquals(55, Collision.yOverlap(brick, ball), 0.0);
    }
}
