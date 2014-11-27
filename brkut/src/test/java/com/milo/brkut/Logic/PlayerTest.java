package com.milo.brkut.Logic;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author milo
 */
public class PlayerTest {

    public PlayerTest() {
    }

    /**
     * Test of getLives method, of class Player.
     */
    @Test
    public void testGetLives() {
        System.out.println("getLives");
        Player instance = new Player(10, 10, 10, 10);
        int expResult = 3;
        int result = instance.getLives();
        assertEquals(expResult, result);
    }

    /**
     * Test of addLife and removeLife methods, of class Player.
     */
    @Test
    public void testAddAndRemoveLife() {
        System.out.println("addLife & removeLife");
        Player instance = new Player(10,10,10,10);
        instance.addLife();
        instance.addLife();
        instance.kill();
        instance.kill();
        instance.kill();
        assertEquals(instance.getLives(), 2);

    }
}
