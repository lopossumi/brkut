package com.milo.brkut.Logic;

import com.milo.brkut.Logic.Collision.bounce;
import org.junit.Test;
import static org.junit.Assert.*;
import static com.milo.brkut.Logic.Collision.collision;
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
        GameObject ball = new Ball(0,0,10,10);
        GameObject brick = new Brick(100,100,100,10);
        //no collision
        assertEquals(bounce.NONE, collision(ball, brick));
        
        //vertical collision from bottom
        ball.move(60,91);
        assertEquals(bounce.VERTICAL, collision(ball, brick));
        }

    /**
     * Test of move method, of class GameObject.
     */
    @Test
    public void testMove() {
        System.out.println("Move");
        GameObject instance = new Ball(0,0,10,10);
        instance.move(10, 10);
        assertEquals(10.0, instance.getX(),0.0);
        assertEquals(10.0, instance.getY(),0.0);
        }

}
