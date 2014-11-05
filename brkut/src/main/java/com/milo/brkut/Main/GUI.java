package com.milo.brkut.Main;

import com.milo.brkut.Logic.Arena;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @author milo
 */
public class GUI implements Runnable {

    private JFrame frame;
    private Arena arena;
    private Panel window;
    
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    public GUI(Arena arena) {
        this.arena = arena;
    }

    @Override
    public void run() {
        frame = new JFrame("BRKUT");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        
        //NappaimistonKuuntelija kuuntelija = new NappaimistonKuuntelija(maailma.getPelihahmo());
        //frame.addKeyListener(kuuntelija);
        
        frame.pack();
        frame.setVisible(true);
    }

    public void createComponents(Container container) {
        window = new Panel(arena);
        container.add(window);
    }

    // tarjotaan Kayttoliittyman ulkopuolelta pääsy ikkunan piirtämiseen
    public void draw() {
        if (window == null) {
            return;
        }

        window.repaint();
    }

    public JFrame getFrame() {
        return frame;
    }
}