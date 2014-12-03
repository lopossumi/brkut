package com.milo.brkut.Main;

import com.milo.brkut.Engine.GUI;
import com.milo.brkut.Engine.Engine;
import com.milo.brkut.Logic.*;
import javax.swing.SwingUtilities;

/**
 * @author milo
 */
public class Main {

    public static void main(String[] args) {
        Arena arena;
        arena = new Arena(HighscoreIO.read());
        GUI gui = new GUI(arena);
        Engine engine = new Engine(gui, arena);
        SwingUtilities.invokeLater(gui);

        engine.start();
        HighscoreIO.update(arena.getScore());
    }
}
