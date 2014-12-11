package com.milo.brkut.logic;

//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
import com.milo.brkut.logic.Brick;
import java.awt.Color;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author milo
 */
public class BrickTest {

    public BrickTest() {
    }

    /**
     * Test of damage method, of class Brick.
     */
    @Test
    public void testHPandDamage() {
        System.out.println("damage");
        Brick instance = new Brick(0, 0, 10, 10);
        assertEquals(2, instance.getHitpoints());
        assertEquals(instance.getColor(), Color.GREEN);
        instance.damage(1);
        assertEquals(instance.getColor(), Color.RED);
        
        instance.damage(5);
        // Not alive anymore
        assertEquals(0, instance.getHitpoints());
        assertTrue(!instance.isAlive());
    }
}
