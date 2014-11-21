package com.milo.brkut.Main;

import com.milo.brkut.Logic.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author milo
 */
public class Engine extends Thread {

	private GUI gui;
	private Arena arena;
	private boolean running;
        private SoundEngine sounds;

	public Engine(GUI gui, Arena arena) {
		this.gui = gui;
		this.arena = arena;
		this.running = true;
                this.sounds = new SoundEngine();
	}

	@Override
	public void run() {
		while (this.running) {
			update();
			draw();
			hold();
		}
	}

	/**
	 * Update the game arena.
	 */
	public void update() {
                arena.step(this.gui.input().update());
                if(arena.getStatus()==GameStatus.HIT)
                    try {
                        this.sounds.hit();
                } catch (Exception ex) {
                    Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
                }
        }

	/**
	 * Draw updated game arena on screen.
	 */
	public void draw() {
		gui.draw();
	}
	
	public void close() {
		running = false;
	}

	/**
	 * Wait for the next calculation step.
	 */
	public void hold() {
		try {
			Thread.sleep(1000 / 60);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
