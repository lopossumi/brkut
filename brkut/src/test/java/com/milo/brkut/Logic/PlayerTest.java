package com.milo.brkut.Logic;

import com.milo.brkut.Main.Config;
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
    
    @Test
    public void testSpawn() {
        Player instance = new Player(10,10,10,10);
        instance.setVx(10);
        instance.kill();
        assertTrue(!instance.isAlive());
        instance.respawn(Config.PLAYER_START_X, Config.PLAYER_START_Y);
        assertTrue(instance.isAlive());
        assertEquals(Config.PLAYER_START_X, instance.getX(),0.0);
        assertEquals(Config.PLAYER_START_Y, instance.getY(),0.0);
        assertEquals(0, instance.getVx(),0.0);
    }
}
