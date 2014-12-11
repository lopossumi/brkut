package com.milo.brkut.engine;

import java.io.*;
import sun.audio.*;

/**
 *
 * @author milo
 */
public class SoundEngine {

    public SoundEngine() {
    }

    public void hit1() {
        play("media/hit1.wav");
    }

    public void hit2() {
        play("media/hit2.wav");
    }

    public void gameover() {
        play("media/gameover.wav");
    }

    public void died() {
        play("media/died.wav");
    }

    private void play(String sample) {
        try {
            InputStream in = new FileInputStream(sample);
            AudioStream audio = new AudioStream(in);
            AudioPlayer.player.start(audio);
        } catch (Exception ex) {
        }
    }
}
