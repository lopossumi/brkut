package com.milo.brkut.engine;

import com.milo.brkut.logic.Logic;
import com.milo.brkut.main.Config;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Graphical user interface for the game engine.
 * @author milo
 */
public class GUI implements Runnable {

    private JFrame window;
    private Logic logic;
    private Panel panel;
    private UserInput input;

    /**
     * Creates a new gui and binds the keyboard input to player one.
     * @param logic Game logic
     */
    public GUI(Logic logic) {
        this.logic = logic;
        this.input = new UserInput(this.logic.getPlayerOne());
    }

    /**
     * Starts the GUI by drawing the window.
     */
    @Override
    public void run() {
        window = new JFrame("BRKUT");
        window.setResizable(false);
        window.setPreferredSize(new Dimension(Config.ARENA_WIDTH, Config.ARENA_HEIGHT));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(window.getContentPane());
        window.addKeyListener(this.input);
        window.pack();
        window.setVisible(true);
    }

    /**
     * Adds the Panel (JPanel extension) to the top level container.
     * @param container 
     */
    public void createComponents(Container container) {
        panel = new Panel(logic);
        container.add(panel);
    }

    /**
     * Repaints the window.
     */
    public void draw() {
        if (panel == null) {
            return;
        }

        panel.repaint();
    }

    public JFrame getFrame() {
        return window;
    }

    public UserInput input() {
        return this.input;
    }

    public void setLogic(Logic logic) {
        this.logic = logic;
        this.panel.setLogic(logic);
    }
}
