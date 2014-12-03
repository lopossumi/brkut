package com.milo.brkut.Engine;

import com.milo.brkut.Logic.Logic;
import com.milo.brkut.Main.Config;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @author milo
 */

public class GUI implements Runnable {

    private JFrame window;
    private Logic logic;
    private Panel panel;
    private UserInput input;
    
    public GUI(Logic logic) {
        this.logic = logic;
        this.input = new UserInput(this.logic.getPlayerOne());
    }

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

    public void createComponents(Container container) {
        panel = new Panel(logic);
        container.add(panel);
    }

    public void draw() {
        if (panel == null) {
            return;
        }

        panel.repaint();
    }

    public JFrame getFrame() {
        return window;
    }
    
    public UserInput input(){
        return this.input;
    }
}