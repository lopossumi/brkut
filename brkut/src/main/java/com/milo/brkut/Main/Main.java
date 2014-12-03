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
        Logic logic = new Logic(HighscoreIO.read());
        GUI gui = new GUI(logic);
        Engine engine = new Engine(gui, logic);
        SwingUtilities.invokeLater(gui);

        engine.start();
        HighscoreIO.update(logic.getScore());
    }
}
