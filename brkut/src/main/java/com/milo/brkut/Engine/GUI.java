package com.milo.brkut.Engine;

import com.milo.brkut.Logic.Arena;
import com.milo.brkut.Main.Config;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @author milo
 */

public class GUI implements Runnable {

    private JFrame frame;
    private Arena arena;
    private Panel window;
    private UserInput input;
    
    private final int WIDTH = Config.ARENA_WIDTH;
    private final int HEIGHT = Config.ARENA_HEIGHT;

    public GUI(Arena arena) {
        this.arena = arena;
        this.input = new UserInput(this.arena.getPlayerOne());
    }

    @Override
    public void run() {
        frame = new JFrame("BRKUT");
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(frame.getContentPane());

        
        //UserInput input = new UserInput(this.arena.getPlayerOne());
        frame.addKeyListener(this.input);
        
        frame.pack();
        frame.setVisible(true);
    }

    public void createComponents(Container container) {
        window = new Panel(arena);
        container.add(window);
    }

    public void draw() {
        if (window == null) {
            return;
        }

        window.repaint();
    }

    public JFrame getFrame() {
        return frame;
    }
    
    public UserInput input(){
        return this.input;
    }
}