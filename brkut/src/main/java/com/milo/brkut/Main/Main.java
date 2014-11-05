package com.milo.brkut.Main;

import com.milo.brkut.Logic.*;
import javax.swing.SwingUtilities;

/**
 * @author milo
 */
public class Main {

	public static void main(String[] args) {
		Arena arena = new Arena();
                GUI gui = new GUI(arena);
                Engine engine = new Engine(gui, arena);
                SwingUtilities.invokeLater(gui);

                engine.start();
	}
}
