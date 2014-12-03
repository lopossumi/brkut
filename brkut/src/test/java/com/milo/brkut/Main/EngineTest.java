package com.milo.brkut.Main;

import com.milo.brkut.Engine.GUI;
import com.milo.brkut.Engine.Engine;
import com.milo.brkut.Logic.Arena;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author milo
 */
public class EngineTest {
    
    public EngineTest() {
    }
    
    /**
     * Test of run method, of class Engine.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        //Engine instance = null;
        //instance.run();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class Engine.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        //Engine instance = null;
        //instance.update();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of draw method, of class Engine.
     */
    @Test
    public void testDraw() {
        System.out.println("draw");
        //Engine instance = null;
        //instance.draw();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of close method, of class Engine.
     */
    @Test
    public void testClose() {
        System.out.println("close");
        Arena a = new Arena(0);
        GUI g = new GUI(a);
        Engine instance = new Engine(g,a);
        instance.close();
        assert(instance.isAlive() == false);
    }

    /**
     * Test of hold method, of class Engine.
     */
    @Test
    public void testHold() {
        System.out.println("hold");
        //Engine instance = null;
        //instance.hold();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
