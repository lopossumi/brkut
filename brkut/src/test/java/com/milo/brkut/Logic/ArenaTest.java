package com.milo.brkut.Logic;

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
        Arena instance = new Arena();
        
        // After 1000 steps, we should have hit something. Anything.
        for(int i=0; i<1000; i++){
            instance.step();
        }
        assertTrue(instance.getScore()>0);
    }

    /**
     * Test of getStatus method, of class Arena.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        Arena instance = new Arena();
        GameStatus expResult = GameStatus.RUNNING;
        GameStatus result = instance.getStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBricks method, of class Arena.
     */
    @Test
    public void testGetBricks() {
        System.out.println("getBricks");
        Arena instance = new Arena();
        int expResult = 60;
        HashSet<Brick> result = instance.getBricks();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getPlayerOne method, of class Arena.
     */
    @Test
    public void testGetPlayerOne() {
        System.out.println("getPlayerOne");
        Arena instance = new Arena();
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
        Arena instance = new Arena();
        int expResult = -1;
        Ball result = instance.getBall();
        assertEquals(expResult, result.getVy(), 0.0);
    }
    
}
