package com.milo.brkut.main;

import com.milo.brkut.logic.Logic;
import com.milo.brkut.engine.GUI;
import com.milo.brkut.engine.Engine;
import javax.swing.SwingUtilities;

/**
 * BRKUT - Yet another breakout clone.
 * @author milo
 */
public class Main {

    public static void main(String[] args) {
        Logic logic = new Logic(HighscoreIO.read());
        GUI gui = new GUI(logic);
        Engine engine = new Engine(gui, logic);
        SwingUtilities.invokeLater(gui);

        engine.start();
    }
}
