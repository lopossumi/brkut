package com.milo.brkut.engine;

import com.milo.brkut.main.Config;
import java.io.*;
import sun.audio.*;

/**
 * A simple sound engine to play sound effects.
 *
 * @author milo
 */
public class SoundEngine {

    public SoundEngine() {
    }

    /**
     * Plays the ball-hits-brick-sound.
     */
    public void hit1() {
        play("media/hit1.wav");
    }

    /**
     * Plays the ball-hits-paddle-or-wall-sound.
     */
    public void hit2() {
        play("media/hit2.wav");
    }

    /**
     * Plays the game over -sound.
     */
    public void gameover() {
        play("media/gameover.wav");
    }

    /**
     * Plays the player died -sound.
     */
    public void died() {
        play("media/died.wav");
    }

    /**
     * Tries to play a sound sample.
     *
     * @param sample Sample filename
     */
    private void play(final String sample) {
        if (Config.SOUNDS) {
            new Thread() {
                public void run() {
                    try {
                        InputStream in = new FileInputStream(sample);
                        AudioStream audio = new AudioStream(in);
                        AudioPlayer.player.start(audio);
                    } catch (Exception ex) {
                    }
                }
            }.start();
        }
    }
}
