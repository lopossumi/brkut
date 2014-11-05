package com.milo.brkut.Main;

import com.milo.brkut.Logic.*;

/**
 * @author milo
 */
public class Engine extends Thread {

	private GUI gui;
	private Arena arena;
	private boolean running;

	public Engine(GUI gui, Arena arena) {
		this.gui = gui;
		this.arena = arena;
		this.running = true;
	}

	@Override
	public void run() {
		while (running) {
			update();
			draw();
			hold();
		}
	}

	public void update() {
		// Todo
	}

	public void draw() {
		gui.draw();
	}

	public void close() {
		running = false;
	}

	public void hold() {
		try {
			Thread.sleep(1000 / 60);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
