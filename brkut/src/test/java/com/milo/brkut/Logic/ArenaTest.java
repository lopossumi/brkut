package com.milo.brkut.Logic;

import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
        instance.step();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatus method, of class Arena.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        Arena instance = new Arena();
        GameStatus expResult = null;
        GameStatus result = instance.getStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBricks method, of class Arena.
     */
    @Test
    public void testGetBricks() {
        System.out.println("getBricks");
        Arena instance = new Arena();
        HashSet<Brick> expResult = null;
        HashSet<Brick> result = instance.getBricks();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerOne method, of class Arena.
     */
    @Test
    public void testGetPlayerOne() {
        System.out.println("getPlayerOne");
        Arena instance = new Arena();
        Player expResult = null;
        Player result = instance.getPlayerOne();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBall method, of class Arena.
     */
    @Test
    public void testGetBall() {
        System.out.println("getBall");
        Arena instance = new Arena();
        Ball expResult = null;
        Ball result = instance.getBall();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
