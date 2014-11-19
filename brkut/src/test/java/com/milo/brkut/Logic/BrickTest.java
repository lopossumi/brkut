package com.milo.brkut.Logic;

//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
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
        Brick instance = new Brick(0,0,10,10);
        assertEquals(2, instance.getHitpoints());
        instance.damage(3);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(0, instance.getHitpoints());
    }
}
